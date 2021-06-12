package com.epam.web.command;

import com.epam.web.entitiy.User;
import com.epam.web.exception.ServiceException;
import com.epam.web.service.UserService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;

public class ShowBalanceCommand implements Command {

    private final static String USER = "user";
    private final static String BALANCE = "balance";
    private final static String BALANCE_PAGE = "/WEB-INF/view/balance.jsp";

    private final UserService service;

    public ShowBalanceCommand(UserService service) {
        this.service = service;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        HttpSession session = request.getSession();
        if("false".equals(request.getParameter("showMes"))) {
            session.removeAttribute("toppedUpSuccessfully");
        }
        User user = (User) request.getSession().getAttribute(USER);
        BigDecimal balance = service.getCurrentUserBalance(user);
        request.setAttribute(BALANCE, balance);
        return CommandResult.forward(BALANCE_PAGE);
    }

}
