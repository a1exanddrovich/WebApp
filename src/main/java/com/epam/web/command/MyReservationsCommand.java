package com.epam.web.command;

import com.epam.web.dto.ReservationDto;
import com.epam.web.entitiy.Order;
import com.epam.web.entitiy.Reservation;
import com.epam.web.entitiy.User;
import com.epam.web.exception.ServiceException;
import com.epam.web.service.OrderService;
import com.epam.web.service.ReservationService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

public class MyReservationsCommand implements Command {

    private final static int RECORDS_PER_PAGE = 9;

    private final ReservationService reservationService;
    private final OrderService orderService;

    public MyReservationsCommand(ReservationService reservationService, OrderService orderService) {
        this.reservationService = reservationService;
        this.orderService = orderService;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        int currentPage = Integer.parseInt(request.getParameter("currentPage"));
        User user = (User) request.getSession().getAttribute("user");
        List<Reservation> reservationList = reservationService.getCurrentUserReservations(user, currentPage, RECORDS_PER_PAGE);
        List<Order> orderList = orderService.getCurrentUserAllOrders(user);
        List<ReservationDto> reservations = new ArrayList<>();
        for (int i = 0; i < reservationList.size(); i++) {
            Reservation reservation = reservationList.get(i);
            Order order = orderList.get(i);
            if (order.getId() == reservation.getOrderId()) {
                reservations.add(new ReservationDto(reservation.getId(),
                                                    order.getHotelName(),
                                                    order.getRoomClass(),
                                                    order.getPlaceCount(),
                                                    order.getArrivalDate(),
                                                    order.getDepartureDate(),
                                                    reservation.getPrice(),
                                                    reservation.isPaid()));
            }
        }
        request.setAttribute("reservations", reservations);
        int reservationCount = reservationService.getReservationCount(user.getId());
        int pageNumber = reservationCount / RECORDS_PER_PAGE;
        if (pageNumber % RECORDS_PER_PAGE > 0 && !(reservationCount % RECORDS_PER_PAGE == 0)) {
            pageNumber++;
        }
        request.setAttribute("pageNumber", pageNumber);
        request.setAttribute("currentPage", currentPage);
        return CommandResult.forward("/WEB-INF/view/myreservations.jsp");
    }
}
