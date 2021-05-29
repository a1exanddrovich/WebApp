package com.epam.web.extractor;

import com.epam.web.entitiy.Order;
import com.epam.web.entitiy.OrderStatus;
import com.epam.web.entitiy.User;

import javax.servlet.http.HttpServletRequest;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class OrderExtractor implements Extractor<Order> {

    private final static String DATE_FORMAT = "yyyy-MM-dd";
    private final DateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);

    @Override
    public Order extract(HttpServletRequest request) throws NumberFormatException, ParseException {
        User user = (User) request.getSession().getAttribute("user");
        String hotelName = request.getParameter("hotelName");
        String roomClass = request.getParameter("class");
        String orderId = request.getParameter("orderId");
        long id = 0;
        if (orderId != null) {
            id = Long.parseLong(orderId);
        }
        int places = Integer.parseInt(request.getParameter("places"));
        Date arrivalDate = dateFormat.parse(request.getParameter("arrival"));
        Date departureDate = dateFormat.parse(request.getParameter("departure"));
        Order extractedOrder = new Order(id, user.getId(), hotelName, roomClass, places, arrivalDate, departureDate, OrderStatus.PROCESSING);
        return extractedOrder;
    }

}
