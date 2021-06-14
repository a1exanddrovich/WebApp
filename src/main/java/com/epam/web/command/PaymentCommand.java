package com.epam.web.command;

import com.epam.web.entitiy.User;
import com.epam.web.exception.ServiceException;
import com.epam.web.service.UserService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class PaymentCommand implements Command {

    private final static String MY_RESERVATIONS_COMMAND = "controller?command=myReservations&currentPage=1";
    private final static String MY_RESERVATIONS_PAGE = "/controller?command=myReservations&currentPage=1";
    private final static String USER = "user";
    private final static String RESERVATION_ID = "reservationId";
    private final static String PAID_SUCCESSFULLY = "paidSuccessfully";

    private final UserService userService;

    public PaymentCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        HttpSession session = request.getSession();
        User user = (User) request.getSession().getAttribute(USER);
        long reservationId = Long.parseLong(request.getParameter(RESERVATION_ID));
        boolean successfulPayment = userService.withdraw(user.getId(), reservationId);

        if (successfulPayment) {
            session.setAttribute(PAID_SUCCESSFULLY, true);
            return CommandResult.redirect(MY_RESERVATIONS_COMMAND);
        } else {
            request.setAttribute(PAID_SUCCESSFULLY, false);
            return CommandResult.forward(MY_RESERVATIONS_PAGE);
        }
    }

}
