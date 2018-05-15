package com.yuratrumpe.dao;

import com.yuratrumpe.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

@Repository
@Transactional(transactionManager = "jdbcTransactionManager")
public class UserDaoJdbcImpl implements UserDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static final String SELECT_ALL_SQL = "SELECT id, user_name, user_password, user_role FROM users";

    private static final String SELECT_BY_ID_SQL = "SELECT id, user_name, user_password, user_role FROM users WHERE id=?";

    private static final String SELECT_BY_NAME_SQL = "SELECT id, user_name, user_password, user_role FROM users WHERE user_name=?";

    private static final String INSERT_SQL = "INSERT INTO users(user_name, user_password, user_role) VALUES (?, ?, ?)";

    private static final String DELETE_SQL = "DELETE FROM users WHERE id=?";

    private static final String UPDATE_SQL = "UPDATE users SET user_name=?, user_password=?, user_role=? WHERE id=?";

    private static final class UserMapper implements RowMapper<User> {
        @Override
        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
            User user = new User();

            user.setId(rs.getLong("id"));
            user.setUsername(rs.getString("user_name"));
            user.setPassword(rs.getString("user_password"));
            user.setRole(rs.getString("user_role"));

            return user;
        }
    }

    @Override
    public List<User> loadAllUsers() {

        return this.jdbcTemplate.query(SELECT_ALL_SQL, new UserMapper());

    }

    @Override
    public User loadUserById(Long userId) {

        return this.jdbcTemplate.queryForObject(SELECT_BY_ID_SQL, new Object[]{userId}, new UserMapper());
    }

    @Override
    public User loadUserByName(String userName) {
        try {
         return this.jdbcTemplate.queryForObject(SELECT_BY_NAME_SQL, new Object[]{userName}, new UserMapper());
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public Long storeUser(User user) {

        if (user.getId() == null) {

            KeyHolder keyHolder = new GeneratedKeyHolder();

            this.jdbcTemplate.update(new PreparedStatementCreator() {
                @Override
                public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                    PreparedStatement ps = con.prepareStatement(INSERT_SQL, Statement.RETURN_GENERATED_KEYS);
                    ps.setString(1, user.getUsername());
                    ps.setString(2, user.getPassword());
                    ps.setString(3, user.getRole());
                    return ps;
                }
            }, keyHolder);

            return keyHolder.getKey().longValue();
        }

        return null;
    }

    @Override
    public void updateUser(User user) {

        if (user.getId() != null) {

            this.jdbcTemplate.update(UPDATE_SQL, user.getUsername(), user.getPassword(), user.getRole(), user.getId());

        }
    }

    @Override
    public void deleteUser(Long userId) {

        if (userId != null) {

            this.jdbcTemplate.update(DELETE_SQL, userId);
        }
    }

    @Override
    public List<String> loadAllExistRoleNames() {

        List<User> userList = loadAllUsers();
        List<String> roleList = new LinkedList<>();

        for (User user : userList) {
        roleList.add(user.getRole());
        }
        return roleList;
    }
}
