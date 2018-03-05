package com.yuratrumpe.services;

import com.yuratrumpe.model.User;
import java.util.List;

public interface UserService {

    List<User> getAllUsers();

    User getUserById(Long userId);

    Long addUser(String userName, String userPassword);

    void deleteUser(Long userId);

    void updateUser(Long userId, String userName, String userPassword);

    void closeResource();

}
