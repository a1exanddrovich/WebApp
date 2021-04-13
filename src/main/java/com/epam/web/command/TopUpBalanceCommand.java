package com.epam.web.command;

import com.epam.web.entitiy.User;
import com.epam.web.service.UserService;
import com.epam.web.validator.Validator;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TopUpBalanceCommand implements Command {

    private final UserService service;
    private final Validator validator = new Validator();

    public TopUpBalanceCommand(UserService service) {
        this.service = service;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        User user = (User) request.getSession().getAttribute("user");
        String topUpBalance = request.getParameter("topUpBalance");
        double balance = Double.parseDouble(topUpBalance);
        if(validator.validateSum(balance)) {
            service.topUpBalance(balance, user.getId());
        } else  {
            request.setAttribute("error", "something");
            return CommandResult.forward("/controller?command=showBalance");
        }
        return CommandResult.forward("/WEB-INF/view/successfulpage.jsp");
    }

}
