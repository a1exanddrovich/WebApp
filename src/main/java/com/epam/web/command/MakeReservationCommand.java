package com.epam.web.command;

import com.epam.web.entitiy.Order;
import com.epam.web.entitiy.Reservation;
import com.epam.web.entitiy.Room;
import com.epam.web.service.OrderService;
import com.epam.web.service.ReservationService;
import com.epam.web.service.RoomService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MakeReservationCommand implements Command {

    private final ReservationService reservationService;
    private final RoomService roomService;
    private final OrderService orderService;

    public MakeReservationCommand(ReservationService reservationService, RoomService roomService, OrderService orderService) {
        this.reservationService = reservationService;
        this.roomService = roomService;
        this.orderService = orderService;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        long orderId = Long.parseLong(request.getParameter("orderId"));
        long hotelId = Long.parseLong(request.getParameter("hotelId"));
        long roomId = Long.parseLong(request.getParameter("roomId"));
        long userId = Long.parseLong(request.getParameter("userId"));
        Room room = roomService.findRoomById(roomId);
        Order order = orderService.findOrderById(orderId);
        Reservation reservation = new Reservation(orderId, hotelId, roomId, userId);
        reservationService.makeReservation(reservation, order, room);
        return CommandResult.forward("/WEB-INF/view/admin/adminsuccessfulpage.jsp");
    }
}
