package com.epam.web.command;

import com.epam.web.entitiy.Order;
import com.epam.web.entitiy.Reservation;
import com.epam.web.entitiy.Room;
import com.epam.web.exception.ServiceException;
import com.epam.web.service.OrderService;
import com.epam.web.service.ReservationService;
import com.epam.web.service.RoomService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;

public class MakeReservationCommand implements Command {

    private final static String ORDER_ID = "orderId";
    private final static String HOTEL_ID = "hotelId";
    private final static String ROOM_ID = "roomId";
    private final static String USER_ID = "userId";
    private final static String ALL_ORDERS_PAGE = "controller?command=adminAllOrders&currentPage=1";

    private final ReservationService reservationService;
    private final RoomService roomService;
    private final OrderService orderService;

    public MakeReservationCommand(ReservationService reservationService, RoomService roomService, OrderService orderService) {
        this.reservationService = reservationService;
        this.roomService = roomService;
        this.orderService = orderService;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        long orderId = Long.parseLong(request.getParameter(ORDER_ID));
        long hotelId = Long.parseLong(request.getParameter(HOTEL_ID));
        long roomId = Long.parseLong(request.getParameter(ROOM_ID));
        long userId = Long.parseLong(request.getParameter(USER_ID));
        Room room = roomService.findRoomById(roomId);
        Order order = orderService.findOrderById(orderId);
        Reservation reservation = new Reservation(0, orderId, hotelId, roomId, userId, new BigDecimal(0), false);
        reservationService.makeReservation(reservation, order, room);
        return CommandResult.redirect(ALL_ORDERS_PAGE);
    }
}
