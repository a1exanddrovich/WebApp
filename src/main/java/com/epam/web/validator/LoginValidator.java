package com.epam.web.validator;

import com.epam.web.entitiy.User;

public class LoginValidator implements Validator<User> {

    private final static String INPUT_PATTERN = "[a-zA-Z1-9]{5,50}";

    @Override
    public boolean validate(User entity) {
        String login = entity.getLogin();
        String password = entity.getPassword();

        return login.matches(INPUT_PATTERN) && password.matches(INPUT_PATTERN);
    }

}
