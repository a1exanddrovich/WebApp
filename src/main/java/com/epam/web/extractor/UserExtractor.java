package com.epam.web.extractor;

import com.epam.web.entitiy.User;
import com.epam.web.entitiy.UserRole;
import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;

public class UserExtractor implements Extractor<User> {

    private final static String USERNAME = "username";
    private final static String PASSWORD = "password";

    @Override
    public User extract(HttpServletRequest request) {
        String username = request.getParameter(USERNAME);
        String password = request.getParameter(PASSWORD);

        User extractedUser = new User(0, username, password, new BigDecimal(0), UserRole.USER, false);
        return extractedUser;
    }

}
