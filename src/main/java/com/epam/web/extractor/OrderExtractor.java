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
    private final static String USER = "user";
    private final static String HOTEL_NAME = "hotelName";
    private final static String CLASS = "class";
    private final static String ORDER_ID = "orderId";
    private final static String PLACE_COUNT = "places";
    private final static String ARRIVAL = "arrival";
    private final static String DEPARTURE = "departure";
    private final DateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);

    @Override
    public Order extract(HttpServletRequest request) throws NumberFormatException, ParseException {
        User user = (User) request.getSession().getAttribute(USER);
        String hotelName = request.getParameter(HOTEL_NAME);
        String roomClass = request.getParameter(CLASS);
        String orderId = request.getParameter(ORDER_ID);
        long id = 0;
        if (orderId != null) {
            id = Long.parseLong(orderId);
        }
        int places = Integer.parseInt(request.getParameter(PLACE_COUNT));
        Date arrivalDate = dateFormat.parse(request.getParameter(ARRIVAL));
        Date departureDate = dateFormat.parse(request.getParameter(DEPARTURE));

        Order extractedOrder = new Order(id, user.getId(), hotelName, roomClass, places, arrivalDate, departureDate, OrderStatus.PROCESSING);
        return extractedOrder;
    }

}
