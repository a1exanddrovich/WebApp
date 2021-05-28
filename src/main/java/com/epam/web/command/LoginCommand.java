package com.epam.web.command;

import com.epam.web.entitiy.User;
import com.epam.web.entitiy.UserRole;
import com.epam.web.exception.ServiceException;
import com.epam.web.service.UserService;
import com.epam.web.validator.LoginValidator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.Optional;

public class LoginCommand implements Command {

    private final UserService service;
    private final LoginValidator validator;

    public LoginCommand(UserService service, LoginValidator validator) {
        this.service = service;
        this.validator = validator;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        User extractedUser = new User(0, username, password, new BigDecimal(0), UserRole.USER, false);
        if (!validator.validate(extractedUser)) {
            request.setAttribute("errorMessage", "invalid login");
            return CommandResult.forward("/index.jsp");
        }
        Optional<User> optionalUser = service.login(username, password);
        if(optionalUser.isPresent()) {
            User user = optionalUser.get();
            if (user.getIsBlocked()) {
                request.setAttribute("userBlocked", "blocked");
                return CommandResult.forward("/index.jsp");
            }
            request.getSession().setAttribute("user", user);
            if("ADMIN".equals(user.getRole())) {
                return CommandResult.redirect("controller?command=adminAllOrders&currentPage=1");
            }
        } else {
            request.setAttribute("errorMessage", "invalid login");
            return CommandResult.forward("/index.jsp");
        }
        return CommandResult.redirect("controller?command=mainPage&currentPage=1");
    }

}
