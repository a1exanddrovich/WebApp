package com.epam.web.extractor;

import com.epam.web.entitiy.Reservation;
import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;

public class ReservationExtractor implements Extractor<Reservation> {

    private final static String ORDER_ID = "orderId";
    private final static String HOTEL_ID = "hotelId";
    private final static String ROOM_ID = "roomId";
    private final static String USER_ID = "userId";

    @Override
    public Reservation extract(HttpServletRequest request) {
        long orderId = Long.parseLong(request.getParameter(ORDER_ID));
        long hotelId = Long.parseLong(request.getParameter(HOTEL_ID));
        long roomId = Long.parseLong(request.getParameter(ROOM_ID));
        long userId = Long.parseLong(request.getParameter(USER_ID));

        Reservation extractedReservation = new Reservation(0, orderId, hotelId, roomId, userId, new BigDecimal(0), false);
        return extractedReservation;
    }
}
