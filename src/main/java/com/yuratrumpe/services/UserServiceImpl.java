package com.yuratrumpe.services;

import com.yuratrumpe.dao.UserDao;
import com.yuratrumpe.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public List<User> getAllUsers() {
        return userDao.loadAll();
    }

    @Override
    public User getUserById(Long userId) {
        return userDao.loadById(userId);
    }

    @Override
    public User getUserByName(String userName) {
        return userDao.loadUserByName(userName);
    }

    @Override
    public Long addUser(String userName, String userPassword, String userRole) {
        userDao.store(new User(null, userName, userPassword, null));
        return getUserByName(userName).getId();

    }

    @Override
    public void addUser(User user) {
        userDao.store(user);
    }

    @Override
    public void deleteUser(Long userId) {
        userDao.delete(userId);

    }

    @Override
    public void updateUser(Long userId, String userName, String userPassword, String userRole) {
        userDao.update(new User(userId, userName, userPassword, null));
    }

    @Override
    public void updateUser(User user) {
        userDao.update(user);
    }
}
