package com.epam.web.command;

import com.epam.web.entitiy.User;
import com.epam.web.service.HotelService;
import com.epam.web.service.UserService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PaymentCommand implements Command {

    private final UserService userService;
    private final HotelService hotelService;

    public PaymentCommand(UserService userService, HotelService hotelService) {
        this.userService = userService;
        this.hotelService = hotelService;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        User user = (User) request.getSession().getAttribute("user");
        long reservationId = Long.parseLong(request.getParameter("reservationId"));
        userService.withdraw(user.getId(), reservationId);
        return CommandResult.forward("/WEB-INF/view/successfulpage.jsp");
    }

}
