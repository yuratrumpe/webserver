//package com.yuratrumpe.dao;
//
//import com.yuratrumpe.model.User;
//import org.hibernate.Session;
//import org.hibernate.SessionFactory;
//import org.hibernate.Transaction;
//import org.hibernate.query.Query;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Primary;
//import org.springframework.stereotype.Repository;
//import org.springframework.transaction.annotation.Transactional;
//
//import javax.persistence.NoResultException;
//import java.util.LinkedList;
//import java.util.List;
//
//@Repository
//@Primary
//@Transactional(transactionManager = "hibernateTransactionManager")
//public class UserDaoHibernateImpl implements UserDao {
//
//    @Autowired
//    private SessionFactory sessionFactory;
//
//    public void setSessionFactory(SessionFactory sessionFactory) {
//        this.sessionFactory = sessionFactory;
//    }
//
//    @Override
//    public List<User> loadAllUsers() {
//
//        Session session = sessionFactory.getCurrentSession();
//        return session.createQuery("FROM User").list();
//    }
//
//    @Override
//    public User loadUserById(Long userId) {
//
//        Session session = sessionFactory.getCurrentSession();
//        return session.get(User.class, userId);
//
//    }
//
//    @Override
//    public User loadUserByName(String userName) {
//
//        Session session = sessionFactory.getCurrentSession();
//        Query query = session.createQuery("FROM User AS user WHERE user.username = :name");
//        query.setParameter("name", userName);
//        try {
//            User user = (User) query.getSingleResult();
//            return user;
//        } catch (NoResultException e) {
//            return null;
//        }
//
//    }
//
//    @Override
//    public Long storeUser(User user) {
//
//        Session session = sessionFactory.getCurrentSession();
//        session.save(user);
//        return user.getId();
//
//    }
//
//    @Override
//    public void updateUser(User user) {
//
//        Session session = sessionFactory.getCurrentSession();
//        session.update(user);
//    }
//
//    @Override
//    public void deleteUser(Long userId) {
//
//        Session session = sessionFactory.getCurrentSession();
//        session.delete(new User(userId, null, null, null));
////            session.delete(loadUserById(userId));
//    }
//
//    @Override
//    public List<String> loadAllExistRoleNames() {
//
//        List<User> userList = loadAllUsers();
//        List<String> roleList = new LinkedList<>();
//
//        for (User user : userList) {
//            roleList.add(user.getRole());
//        }
//        return roleList;
//    }
//}
