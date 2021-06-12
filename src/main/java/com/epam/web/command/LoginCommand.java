package com.epam.web.command;

import com.epam.web.entitiy.User;
import com.epam.web.exception.ServiceException;
import com.epam.web.extractor.UserExtractor;
import com.epam.web.service.UserService;
import com.epam.web.validator.LoginValidator;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

public class LoginCommand implements Command {

    private final static String ADMIN = "ADMIN";
    private final static String USER_MAIN_PAGE = "controller?command=mainPage&currentPage=1";
    private final static String ADMIN_MAIN_PAGE = "controller?command=adminAllOrders&currentPage=1";
    private final static String INDEX = "/index.jsp";
    private final static String ERROR_MESSAGE = "errorMessage";
    private final static String USER = "user";
    private final static String USER_BLOCKED = "userBlocked";

    private final UserService service;
    private final LoginValidator validator;
    private final UserExtractor extractor;

    public LoginCommand(UserService service, LoginValidator validator, UserExtractor extractor) {
        this.service = service;
        this.validator = validator;
        this.extractor = extractor;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        User extractedUser = extractor.extract(request);
        if (!validator.validate(extractedUser)) {
            request.setAttribute(ERROR_MESSAGE, true);
            return CommandResult.forward(INDEX);
        }
        Optional<User> optionalUser = service.login(extractedUser.getLogin(), extractedUser.getPassword());
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            if (user.getIsBlocked()) {
                request.setAttribute(USER_BLOCKED, true);
                return CommandResult.forward(INDEX);
            }
            request.getSession().setAttribute(USER, user);
            if (ADMIN.equals(user.getRole())) {
                return CommandResult.redirect(ADMIN_MAIN_PAGE);
            }
        } else {
            request.setAttribute(ERROR_MESSAGE, true);
            return CommandResult.forward(INDEX);
        }
        return CommandResult.redirect(USER_MAIN_PAGE);
    }

}
