package com.epam.web.command;

import com.epam.web.entitiy.User;
import com.epam.web.entitiy.UserRole;
import com.epam.web.exception.ServiceException;
import com.epam.web.service.UserService;
import com.epam.web.validator.LoginValidator;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;

public class SignupCommand implements Command {

    private final static String ERROR_MESSAGE = "errorMessage";
    private final static String LOGIN_TAKEN = "loginAlreadyTaken";
    private final static String SIGNUP_PAGE = "/signup.jsp";
    private final static String USER = "user";
    private final static String USER_MAIN_PAGE_COMMAND = "controller?command=mainPage&currentPage=1";
    private final static String REGISTERED_SUCCESSFULLY = "registeredSuccessfully";
    private final static String DIFFERENT_PASSWORDS = "differentPasswordsError";
    private final static String USERNAME = "username";
    private final static String PASSWORD = "passwordFirst";
    private final static String SUBMITTED_PASSWORD = "passwordSecond";

    private final UserService service;
    private final LoginValidator validator;

    public SignupCommand(UserService service, LoginValidator validator) {
        this.service = service;
        this.validator = validator;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        HttpSession session = request.getSession();
        String login = request.getParameter(USERNAME);
        String firstPassword = request.getParameter(PASSWORD);
        String secondPassword = request.getParameter(SUBMITTED_PASSWORD);
        if(!firstPassword.equals(secondPassword)) {
            request.setAttribute(DIFFERENT_PASSWORDS, true);
            return CommandResult.forward(SIGNUP_PAGE);
        }
        User user = new User(0, login, firstPassword, new BigDecimal(0), UserRole.USER, false);
        if (!validator.validate(user)) {
            request.setAttribute(ERROR_MESSAGE, true);
            return CommandResult.forward(SIGNUP_PAGE);
        }
        boolean userExists = service.findByLogin(user);
        if (userExists) {
            request.setAttribute(LOGIN_TAKEN, true);
            return CommandResult.forward(SIGNUP_PAGE);
        }
        service.addUser(user);
        session.setAttribute(USER, user);
        session.setAttribute(REGISTERED_SUCCESSFULLY, true);
        return CommandResult.redirect(USER_MAIN_PAGE_COMMAND);
    }

}
