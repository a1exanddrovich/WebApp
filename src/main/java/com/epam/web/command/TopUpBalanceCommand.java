package com.epam.web.command;

import com.epam.web.entitiy.User;
import com.epam.web.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TopUpBalanceCommand implements Command {

    private final UserService service;

    public TopUpBalanceCommand(UserService service) {
        this.service = service;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        User user = (User) request.getSession().getAttribute("user");
        String topUpBalance = request.getParameter("topUpBalance");
        double balance = Double.parseDouble(topUpBalance);
        service.topUpBalance(balance, user.getId());
        return CommandResult.forward("/WEB-INF/view/successfulpage.jsp");
    }

}
