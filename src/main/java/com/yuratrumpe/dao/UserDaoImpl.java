package com.yuratrumpe.dao;

import com.yuratrumpe.model.User;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.NoResultException;


@Repository
@Transactional
public class UserDaoImpl extends AbstractDao<User> implements UserDao {

    @Override
    public User loadUserByName(String userName) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("FROM User AS user WHERE user.username = :name");
        query.setParameter("name", userName);
        try {
            User user = (User) query.getSingleResult();
            return user;
        } catch (NoResultException e) {
            return null;
        }
    }
}
