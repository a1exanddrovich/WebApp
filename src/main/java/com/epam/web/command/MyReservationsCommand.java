package com.epam.web.command;

import com.epam.web.entitiy.Reservation;
import com.epam.web.service.ReservationService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class MyReservationsCommand implements Command {


    private final ReservationService service;

    public MyReservationsCommand(ReservationService service) {
        this.service = service;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        List<Reservation> reservations = service.getAllReservations();
        request.setAttribute("reservations", reservations);
        return CommandResult.forward("/WEB-INF/view/myreservations.jsp");
    }
}
