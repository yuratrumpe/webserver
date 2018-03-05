package com.yuratrumpe.services;

import com.yuratrumpe.dao.UserDao;
import com.yuratrumpe.dao.factory.UserDaoFactory;
import com.yuratrumpe.model.User;
import com.yuratrumpe.util.DBHelperImpl;
import com.yuratrumpe.util.UserDaoFabricHelper;
import com.yuratrumpe.util.UserDaoFabricHelperImpl;


import java.util.List;

public class UserServiceImpl implements UserService {

    private final UserDaoFactory userDaoFactory;

    private static UserServiceImpl instance;

    private UserServiceImpl() {
        UserDaoFabricHelper userDaoFabricHelper = UserDaoFabricHelperImpl.getInstance();
        userDaoFactory = userDaoFabricHelper.getUserDaoFactory();
    }

    public static synchronized UserServiceImpl getInstance() {
        return (instance == null) ? instance = new UserServiceImpl() : instance;
    }

    @Override
    public List<User> getAllUsers() {
        UserDao dao = userDaoFactory.getUserDao();
        return dao.loadAllUsers();
    }

    @Override
    public User getUserById(Long userId) {
        UserDao dao = userDaoFactory.getUserDao();
        return dao.loadUserById(userId);
    }

    @Override
    public Long addUser(String userName, String userPassword) {
        UserDao dao = userDaoFactory.getUserDao();
        return dao.storeUser(new User(null, userName, userPassword));

    }

    @Override
    public void deleteUser(Long userId) {
        UserDao dao = userDaoFactory.getUserDao();
        dao.deleteUser(userId);

    }

    @Override
    public void updateUser(Long userId, String userName, String userPassword) {
        UserDao dao = userDaoFactory.getUserDao();
        dao.updateUser(new User(userId, userName, userPassword));
    }

    @Override
    public void closeResource() {
        userDaoFactory.getUserDao().closeDbResource();
    }
}
