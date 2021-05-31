package com.epam.web.command;

import com.epam.web.entitiy.Reservation;
import com.epam.web.exception.ServiceException;
import com.epam.web.service.OrderService;
import com.epam.web.service.ReservationService;
import com.epam.web.service.RoomService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

public class RefuseReservationCommand implements Command {

    private final static String RESERVATION_ID = "reservationId";
    private final static String MY_RESERVATIONS_COMMAND = "controller?command=myReservations&currentPage=1";

    private final ReservationService service;

    public RefuseReservationCommand(ReservationService reservationService) {
        this.service = reservationService;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        long reservationId = Long.parseLong(request.getParameter(RESERVATION_ID));
        Optional<Reservation> optionalReservation = service.findById(reservationId);
        service.refuseReservation(optionalReservation.get());
        return CommandResult.redirect(MY_RESERVATIONS_COMMAND);
    }

}
