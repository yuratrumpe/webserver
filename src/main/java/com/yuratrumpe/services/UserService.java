package com.yuratrumpe.services;

import com.yuratrumpe.model.User;
import java.util.List;

public interface UserService {

    List<User> getAllUsers();

    User getUserById(Long userId);

    User getUserByName(String userName);

    Long addUser(String userName, String userPassword, String userRole);

    void addUser(User user);

    void deleteUser(Long userId);

    void updateUser(Long userId, String userName, String userPassword, String userRole);

    void updateUser(User user);

}
