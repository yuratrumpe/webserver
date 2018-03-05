package com.yuratrumpe.util;

import com.yuratrumpe.model.User;
import org.hibernate.cfg.Configuration;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;


public class DBHelperImpl implements DBHelper {

    private static DBHelperImpl instance;

    private Connection jdbcConnection;
    private Configuration hibernateConfiguration;

    private DBHelperImpl() {
    }

    public synchronized static DBHelperImpl getInstance() {
        return (instance == null) ? instance = new DBHelperImpl() : instance;
    }

    @Override
    public Connection getConnection() {
        return (jdbcConnection == null) ? jdbcConnection = createJdbcConnection() : jdbcConnection;
    }

    private Connection createJdbcConnection() {

        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        String daoConfigFile = "db.properties";
        InputStream dbResource = classLoader.getResourceAsStream(daoConfigFile);

        Properties dbProperties = new Properties();

        try {

            dbProperties.load(dbResource);

        } catch (IOException e) {
            throw new RuntimeException("DB configuration exception", e);
        }

//        final String url = "jdbc:mysql://localhost:3306/users_db?&characterEncoding=UTF8&serverTimezone=UTC&useSSL=false&verifyServerCertificate=false";
//        final String user = "dbuser";
//        final String password = "P@ssw0rd";
        try {

            Class.forName(dbProperties.getProperty("driver"));
            return DriverManager.getConnection(dbProperties.getProperty("url"), dbProperties);


        } catch (ClassNotFoundException | SQLException e) {

            throw new RuntimeException("DBHelper exception", e);
        }
    }

    @Override
    public Configuration getConfiguration() {
        return (hibernateConfiguration == null) ? hibernateConfiguration = createHibernateConfiguration() : hibernateConfiguration;
    }

    private Configuration createHibernateConfiguration() {
        return new Configuration();
    }


    @Override
    public void closeDBResources() {

        if (jdbcConnection != null) {
            try {
                jdbcConnection.close();
                System.out.println("Close JDBC");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (hibernateConfiguration != null) {

            hibernateConfiguration.addAnnotatedClass(User.class).
                    configure().buildSessionFactory().close();
            System.out.println("Close Hibernate");
        }

    }
}
