package com.epam.web.command;

import com.epam.web.entitiy.Reservation;
import com.epam.web.service.OrderService;
import com.epam.web.service.ReservationService;
import com.epam.web.service.RoomService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RefuseReservationCommand implements Command {

    private final ReservationService service;

    public RefuseReservationCommand(ReservationService reservationService) {
        this.service = reservationService;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        long reservationId = Long.parseLong(request.getParameter("reservationId"));
        Reservation reservation = service.findById(reservationId);
        service.refuseReservation(reservation);
        return CommandResult.forward("/WEB-INF/view/successfulpage.jsp");
    }

}
