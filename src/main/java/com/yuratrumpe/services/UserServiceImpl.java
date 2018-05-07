package com.yuratrumpe.services;

import com.yuratrumpe.dao.UserDao;
import com.yuratrumpe.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

//@Service("userService")
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public List<User> getAllUsers() {
        return userDao.loadAllUsers();
    }

    @Override
    public User getUserById(Long userId) {
        return userDao.loadUserById(userId);
    }

    @Override
    public User getUserByName(String userName) {
        return userDao.loadUserByName(userName);
    }

    @Override
    public Long addUser(String userName, String userPassword, String userRole) {
        return userDao.storeUser(new User(null, userName, userPassword, userRole));

    }

    @Override
    public void deleteUser(Long userId) {
        userDao.deleteUser(userId);

    }

    @Override
    public void updateUser(Long userId, String userName, String userPassword, String userRole) {
        userDao.updateUser(new User(userId, userName, userPassword, userRole));
    }

}
