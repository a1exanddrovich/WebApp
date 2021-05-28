package com.epam.web.command;

import com.epam.web.entitiy.User;
import com.epam.web.exception.ServiceException;
import com.epam.web.service.UserService;
import com.epam.web.validator.CashValidator;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;

public class TopUpBalanceCommand implements Command {

    private final UserService service;
    private final CashValidator cashValidator = new CashValidator();

    public TopUpBalanceCommand(UserService service) {
        this.service = service;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        User user = (User) request.getSession().getAttribute("user");
        String topUpBalance = request.getParameter("topUpBalance");
        double balanceToTopUp;
        BigDecimal balance;
        try {
            balanceToTopUp = Double.parseDouble(topUpBalance);
        } catch (NumberFormatException e) {
            request.setAttribute("invalidCash", "something");
            return CommandResult.forward("/controller?command=showBalance");
        }
        balance = new BigDecimal(balanceToTopUp);
        if (cashValidator.validate(balance)) {
            service.topUpBalance(balance, user.getId());
        } else {
            request.setAttribute("error", "validationError");
            return CommandResult.forward("/controller?command=showBalance");
        }
        return CommandResult.redirect("controller?command=showBalance");
    }

}
