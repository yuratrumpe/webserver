package com.yuratrumpe.util;

import com.yuratrumpe.dao.factory.UserDaoFactory;
import com.yuratrumpe.dao.factory.UserDaoFactoryHibernateImpl;
import com.yuratrumpe.dao.factory.UserDaoFactoryJdbcImpl;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class UserDaoFabricHelperImpl implements UserDaoFabricHelper {

    private static UserDaoFabricHelperImpl instance;

    private UserDaoFabricHelperImpl() {
    }

    public synchronized static UserDaoFabricHelperImpl getInstance() {
        return (instance == null) ? instance = new UserDaoFabricHelperImpl() : instance;
    }

    @Override
    public UserDaoFactory getUserDaoFactory() {

        DBHelper dbHelper = DBHelperImpl.getInstance();

        PropertiesLoader propertiesLoader = new PropertiesLoaderImpl();
        Properties daoProperties = propertiesLoader.getPropertiesFromFile("dao.properties");

        switch (daoProperties.getProperty("dao.type")) {

            case "hibernate":
                return new UserDaoFactoryHibernateImpl(dbHelper);
            case "jdbc":
                return new UserDaoFactoryJdbcImpl(dbHelper);
            default:
                return new UserDaoFactoryJdbcImpl(dbHelper);

        }
    }

}
