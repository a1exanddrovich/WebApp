package com.epam.web.mapper;

import com.epam.web.entitiy.User;
import com.epam.web.entitiy.UserRole;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper implements Mapper<User> {

    private static final String ID = "id";
    private static final String LOGIN = "login";
    private static final String PASSWORD = "password";
    private static final String BALANCE = "balance";
    private static final String ROLE = "role";
    private static final String IS_BLOCKED = "is_blocked";

    @Override
    public User map(ResultSet resultSet) throws SQLException {
        long userId = resultSet.getLong(ID);
        String userLogin = resultSet.getString(LOGIN);
        String userPassword = resultSet.getString(PASSWORD);
        UserRole userRole = UserRole.valueOf(resultSet.getString(ROLE));
        BigDecimal userBalance = resultSet.getBigDecimal(BALANCE);
        boolean isBlocked = resultSet.getBoolean(IS_BLOCKED);

        User user = new User(userId, userLogin, userPassword, userBalance, userRole, isBlocked);
        return user;
    }

}
