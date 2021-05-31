package com.epam.web.command;

import com.epam.web.entitiy.Order;
import com.epam.web.entitiy.Reservation;
import com.epam.web.entitiy.Room;
import com.epam.web.exception.ServiceException;
import com.epam.web.extractor.ReservationExtractor;
import com.epam.web.service.OrderService;
import com.epam.web.service.ReservationService;
import com.epam.web.service.RoomService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

public class MakeReservationCommand implements Command {

    private final static String ALL_ORDERS_COMMAND = "controller?command=adminAllOrders&currentPage=1";

    private final ReservationService reservationService;
    private final RoomService roomService;
    private final OrderService orderService;
    private final ReservationExtractor extractor;

    public MakeReservationCommand(ReservationService reservationService, RoomService roomService, OrderService orderService, ReservationExtractor extractor) {
        this.reservationService = reservationService;
        this.roomService = roomService;
        this.orderService = orderService;
        this.extractor = extractor;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        Reservation extractedReservation = extractor.extract(request);
        Optional<Room> optionalRoom = roomService.findRoomById(extractedReservation.getRoomId());
        Optional<Order> optionalOrder = orderService.findOrderById(extractedReservation.getOrderId());
        reservationService.makeReservation(extractedReservation, optionalOrder.get(), optionalRoom.get());
        return CommandResult.redirect(ALL_ORDERS_COMMAND);
    }
}
