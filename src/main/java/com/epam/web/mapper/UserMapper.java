package com.epam.web.mapper;

import com.epam.web.entitiy.User;
import com.epam.web.entitiy.UserRole;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper implements Mapper<User> {

    public static final String ID = "id";
    public static final String LOGIN = "login";
    public static final String PASSWORD = "password";
    public static final String ROLE = "role";

    @Override
    public User map(ResultSet resultSet) throws SQLException {
        long userId = resultSet.getLong(ID);
        String userLogin = resultSet.getString(LOGIN);
        String userPassword = resultSet.getString(PASSWORD);
        UserRole userRole = UserRole.valueOf(resultSet.getString(ROLE));
        User user = new User(userId, userLogin, userPassword, userRole);
        return user;
    }

}
