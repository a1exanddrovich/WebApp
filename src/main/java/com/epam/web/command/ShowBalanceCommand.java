package com.epam.web.command;

import com.epam.web.entitiy.User;
import com.epam.web.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class ShowBalanceCommand implements Command {

    private final UserService service;

    public ShowBalanceCommand(UserService service) {
        this.service = service;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        User user = (User) request.getSession().getAttribute("user");
        BigDecimal balance = service.getCurrentUserBalance(user);
        request.setAttribute("balance", balance);
        return CommandResult.forward("/WEB-INF/view/balance.jsp");
    }

}
