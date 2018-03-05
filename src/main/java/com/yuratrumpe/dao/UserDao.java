package com.yuratrumpe.dao;


import com.yuratrumpe.model.User;
import java.util.List;

public interface UserDao {

    List<User> loadAllUsers();

    User loadUserById(Long userId);

    Long storeUser(User user);

    void updateUser(User user);

    void deleteUser(Long userId);

    void closeDbResource();

}
