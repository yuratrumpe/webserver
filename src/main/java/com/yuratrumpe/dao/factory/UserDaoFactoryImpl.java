package com.yuratrumpe.dao.factory;

import com.yuratrumpe.dao.UserDao;

public class UserDaoFactoryImpl implements UserDaoFactory {

    private String daoType;
    private UserDao userDaoJDBC;
    private UserDao userDaoHibernate;

    public String getDaoType() {
        return daoType;
    }

    public void setDaoType(String daoType) {
        this.daoType = daoType;
    }

    public UserDao getUserDaoJDBC() {
        return userDaoJDBC;
    }

    public void setUserDaoJDBC(UserDao userDaoJDBC) {
        this.userDaoJDBC = userDaoJDBC;
    }

    public UserDao getUserDaoHibernate() {
        return userDaoHibernate;
    }

    public void setUserDaoHibernate(UserDao userDaoHibernate) {
        this.userDaoHibernate = userDaoHibernate;
    }

    @Override
    public UserDao getUserDao() {
        return daoType.equals("hibernate")?userDaoHibernate:userDaoJDBC;
    }
}
