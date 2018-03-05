package com.yuratrumpe.dao;

import com.mysql.cj.api.jdbc.Statement;
import com.yuratrumpe.model.User;
import com.yuratrumpe.util.DBHelper;
import com.yuratrumpe.util.DBHelperImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJdbcImpl implements UserDao {

    private static final String SELECT_ALL_SQL = "SELECT id, user_name, user_password FROM users";

    private static final String SELECT_BY_ID_SQL = "SELECT id, user_name, user_password FROM users WHERE id=?";

    private static final String INSERT_SQL = "INSERT INTO users(user_name, user_password) VALUES (?, ?)";

    private static final String DELETE_SQL = "DELETE FROM users WHERE id=?";

    private static final String UPDATE_SQL = "UPDATE users SET user_name=?, user_password=? WHERE id=?";

    private final Connection connection;

    public UserDaoJdbcImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<User> loadAllUsers() {

        List<User> allUsers = new ArrayList<>();

        try {
            PreparedStatement statement = connection.prepareStatement(SELECT_ALL_SQL);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Long userId = resultSet.getLong("id");
                String userName = resultSet.getString("user_name");
                String userPassword = resultSet.getString("user_password");
                User user = new User(userId, userName, userPassword);
                allUsers.add(user);
            }
            resultSet.close();
            statement.close();
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return allUsers;
    }

    @Override
    public User loadUserById(Long userId) {

        try {
            PreparedStatement statement = connection.prepareStatement(SELECT_BY_ID_SQL);
            statement.setLong(1, userId);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String userName = resultSet.getString("user_name");
                String userPassword = resultSet.getString("user_password");
                User user = new User(userId, userName, userPassword);
                resultSet.close();
                statement.close();

                return user;
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    @Override
    public Long storeUser(User user) {

        if (user.getId() == null) {

            try {
                PreparedStatement statement = connection.prepareStatement(INSERT_SQL, Statement.RETURN_GENERATED_KEYS);
                statement.setString(1, user.getUserName());
                statement.setString(2, user.getPassword());

                System.out.println(statement);
                statement.executeUpdate();

                ResultSet resultSet = statement.getGeneratedKeys();
                resultSet.next();
                Long result = resultSet.getLong(1);

                resultSet.close();
                statement.close();
                return result;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        return null;

    }

    @Override
    public void updateUser(User user) {

        if (user.getId() != null) {

            try {
                PreparedStatement statement = connection.prepareStatement(UPDATE_SQL);
                statement.setString(1, user.getUserName());
                statement.setString(2, user.getPassword());
                statement.setLong(3, user.getId());

                System.out.println(statement);
                statement.executeUpdate();

                statement.close();

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public void deleteUser(Long userId) {

        if (userId != null) {

            try {
                PreparedStatement statement = connection.prepareStatement(DELETE_SQL);
                statement.setLong(1, userId);

                System.out.println(statement);
                statement.executeUpdate();

                statement.close();

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public void closeDbResource() {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException("Close DB exception", e);
        }
    }
}
