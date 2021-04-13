package com.epam.web.command;

import com.epam.web.entitiy.User;
import com.epam.web.service.UserService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

public class LoginCommand implements Command {

    private final UserService service;

    public LoginCommand(UserService service) {
        this.service = service;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        Optional<User> optionalUser = service.login(username, password);
        if(optionalUser.isPresent()) {
            User user = optionalUser.get();
            request.getSession().setAttribute("user", user);
            if("ADMIN".equals(user.getRole())) {
                return CommandResult.redirect("controller?command=adminAllOrders");
            }
        } else {
            request.setAttribute("errorMessage", "something");
            return CommandResult.forward("/index.jsp");
        }
        return CommandResult.redirect("controller?command=mainPage");
    }

}
