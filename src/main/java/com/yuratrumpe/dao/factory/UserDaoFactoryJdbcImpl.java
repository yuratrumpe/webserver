package com.yuratrumpe.dao.factory;

import com.yuratrumpe.dao.UserDao;
import com.yuratrumpe.dao.UserDaoJdbcImpl;
import com.yuratrumpe.dao.factory.UserDaoFactory;
import com.yuratrumpe.util.DBHelper;

import java.sql.Connection;

public class UserDaoFactoryJdbcImpl implements UserDaoFactory {

    private Connection connection;

    public UserDaoFactoryJdbcImpl(DBHelper dbHelper) {
        this.connection = dbHelper.getConnection();
    }

    @Override
    public UserDao getUserDao() {
        return new UserDaoJdbcImpl(connection);
    }
}
