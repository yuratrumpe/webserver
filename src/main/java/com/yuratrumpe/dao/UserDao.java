package com.yuratrumpe.dao;


import com.yuratrumpe.model.User;
import java.util.List;

public interface UserDao extends GenericDao<User>{

    User loadUserByName(String userName);
}
