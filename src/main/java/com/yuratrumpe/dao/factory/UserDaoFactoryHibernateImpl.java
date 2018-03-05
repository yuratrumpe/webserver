package com.yuratrumpe.dao.factory;

import com.yuratrumpe.dao.UserDao;
import com.yuratrumpe.dao.UserDaoHibernateImpl;
import com.yuratrumpe.dao.factory.UserDaoFactory;
import com.yuratrumpe.model.User;
import com.yuratrumpe.util.DBHelper;
import org.hibernate.SessionFactory;

public class UserDaoFactoryHibernateImpl implements UserDaoFactory {

    private SessionFactory sessionFactory;

    public UserDaoFactoryHibernateImpl(DBHelper dbHelper) {
        this.sessionFactory = dbHelper.getConfiguration().addAnnotatedClass(User.class).
                configure().buildSessionFactory();
    }

    @Override
    public UserDao getUserDao() {
        return new UserDaoHibernateImpl(sessionFactory);
    }
}
