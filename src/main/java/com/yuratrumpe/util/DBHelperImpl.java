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

        PropertiesLoader propertiesLoader = new PropertiesLoaderImpl();
        Properties dbProperties = propertiesLoader.getPropertiesFromFile("db_jdbc.properties");

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

        PropertiesLoader propertiesLoader = new PropertiesLoaderImpl();
        Properties dbProperties = propertiesLoader.getPropertiesFromFile("db_hibernate.properties");

        return new Configuration().setProperties(dbProperties);
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