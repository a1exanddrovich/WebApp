package com.epam.web.command;

import com.epam.web.entitiy.User;
import com.epam.web.exception.ServiceException;
import com.epam.web.service.UserService;
import com.epam.web.validator.CashValidator;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;

public class TopUpBalanceCommand implements Command {

    private final static String USER = "user";
    private final static String TOP_UP_BALANCE = "topUpBalance";
    private final static String BALANCE_PAGE = "/controller?command=showBalance";
    private final static String BALANCE_COMMAND = "controller?command=showBalance";


    private final UserService service;
    private final CashValidator cashValidator = new CashValidator();

    public TopUpBalanceCommand(UserService service) {
        this.service = service;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        User user = (User) request.getSession().getAttribute(USER);
        String topUpBalance = request.getParameter(TOP_UP_BALANCE);
        double balanceToTopUp;
        BigDecimal balance;
        try {
            balanceToTopUp = Double.parseDouble(topUpBalance);
        } catch (NumberFormatException e) {
            request.setAttribute("invalidCash", "something");
            return CommandResult.forward(BALANCE_PAGE);
        }
        balance = new BigDecimal(balanceToTopUp);
        if (cashValidator.validate(balance)) {
            service.topUpBalance(balance, user.getId());
        } else {
            request.setAttribute("error", "validationError");
            return CommandResult.forward(BALANCE_PAGE);
        }
        return CommandResult.redirect(BALANCE_COMMAND);
    }

}
