package com.yuratrumpe.dao.factory;

import com.yuratrumpe.dao.UserDao;

public class UserDaoFactoryImpl implements UserDaoFactory {

    private String daoType;

    public UserDaoFactoryImpl(String daoType) {
        this.daoType = daoType;
    }

    @Override
    public UserDao getUserDao() {
        return null;
    }
}
