package com.epam.web.command;

import com.epam.web.entitiy.Order;
import com.epam.web.entitiy.OrderStatus;
import com.epam.web.entitiy.User;
import com.epam.web.exception.ServiceException;
import com.epam.web.service.OrderService;
import com.epam.web.validator.OrderValidator;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MakeOrderCommand implements Command {

    private final static String DATE_FORMAT = "yyyy-MM-dd";

    private final OrderService service;
    private final DateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
    private final OrderValidator validator;

    public MakeOrderCommand(OrderService service, OrderValidator validator) {
        this.service = service;
        this.validator = validator;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        User user = (User) request.getSession().getAttribute("user");
        String hotelName = request.getParameter("hotelName");
        String roomClass = request.getParameter("class");
        int places = 0;
        try {
            places = Integer.parseInt(request.getParameter("places"));
        } catch (NumberFormatException e) {
            request.setAttribute("invalidPlaceCount", "incorrectData");
            return CommandResult.forward("/WEB-INF/view/booking.jsp");
        }
        Date arrivalDate = null;
        Date departureDate = null;
        try {
            arrivalDate = dateFormat.parse(request.getParameter("arrival"));
            departureDate = dateFormat.parse(request.getParameter("departure"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Order order = new Order(0, user.getId(), hotelName, roomClass, places, arrivalDate, departureDate, OrderStatus.PROCESSING);
        if(validator.validate(order)) {
            service.makeOrder(order);
        } else {
            request.setAttribute("error", "incorrectData");
            return CommandResult.forward("/WEB-INF/view/booking.jsp");
        }
        return CommandResult.redirect("controller?command=booking");
    }

}
