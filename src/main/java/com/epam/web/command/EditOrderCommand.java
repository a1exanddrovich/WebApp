package com.epam.web.command;

import com.epam.web.entitiy.Order;
import com.epam.web.entitiy.OrderStatus;
import com.epam.web.entitiy.User;
import com.epam.web.service.OrderService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class EditOrderCommand implements Command {

    private final OrderService service;
    private final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public EditOrderCommand(OrderService service) {
        this.service = service;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        User user = (User) request.getSession().getAttribute("user");
        String orderId = request.getParameter("orderId");
        long id = Long.parseLong(orderId);
        String hotelName = request.getParameter("hotelName");
        String roomClass = request.getParameter("class");
        int places = Integer.parseInt(request.getParameter("places"));
        Date arrivalDate = null;
        Date departureDate = null;
        try {
            arrivalDate = dateFormat.parse(request.getParameter("arrival"));
            departureDate = dateFormat.parse(request.getParameter("departure"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Order order = new Order(id, user.getId(), hotelName, roomClass, places, arrivalDate, departureDate, OrderStatus.PROCESSING);
        service.editOrder(order);
        return CommandResult.forward("/WEB-INF/view/successfulpage.jsp");
    }

}
