package com.yuratrumpe.dao;

import com.yuratrumpe.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import javax.persistence.NoResultException;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {

    private final SessionFactory sessionFactory;

    public UserDaoHibernateImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<User> loadAllUsers() {

        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        try {
            Query query = session.createQuery("FROM User");
            return query.list();

        } finally {
            transaction.commit();
            session.close();
        }

    }

    @Override
    public User loadUserById(Long userId) {

        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        try {
//            Query query = session.createQuery("FROM User AS user WHERE user.id = :id");
//            query.setParameter("id", userId);
//            return (User) query.getSingleResult();
            return session.get(User.class, userId);

        } finally {
            transaction.commit();
            session.close();
        }
    }

    @Override
    public User loadUserByName(String userName) {

        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        try {
            Query query = session.createQuery("FROM User AS user WHERE user.userName = :name");
            query.setParameter("name", userName);
            User user = (User) query.getSingleResult();
            return user;


        } catch (NoResultException e) {

          return null;

        } finally {
            transaction.commit();
            session.close();
        }
    }

    @Override
    public Long storeUser(User user) {

        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        try {
            session.save(user);
            return user.getId();

        } finally {
            transaction.commit();
            session.close();
        }

    }

    @Override
    public void updateUser(User user) {

        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        try {
            session.update(user);

        } finally {
            transaction.commit();
            session.close();
        }
    }

    @Override
    public void deleteUser(Long userId) {

        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        try {
            session.delete(new User(userId, null, null, null));
//            session.delete(loadUserById(userId));

        } finally {
            transaction.commit();
            session.close();
        }

    }

}
