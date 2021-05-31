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

    private final static String USER = "user";
    private final static String RESERVATIONS = "reservations";
    private final static int RECORDS_PER_PAGE = 9;
    private final static String PAGE_NUMBER = "pageNumber";
    private final static String CURRENT_PAGE = "currentPage";
    private final static String MY_RESERVATIONS_PAGE = "/WEB-INF/view/myreservations.jsp";

    private final ReservationService reservationService;
    private final OrderService orderService;

    public MyReservationsCommand(ReservationService reservationService, OrderService orderService) {
        this.reservationService = reservationService;
        this.orderService = orderService;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        int currentPage = Integer.parseInt(request.getParameter(CURRENT_PAGE));
        User user = (User) request.getSession().getAttribute(USER);
        List<Reservation> reservationList = reservationService.getCurrentUserReservations(user, currentPage, RECORDS_PER_PAGE);
        List<Order> orderList = orderService.getCurrentUserAllOrders(user);
        List<ReservationDto> reservations = new ArrayList<>();
        for (Reservation reservation : reservationList) {
            for (Order order : orderList) {
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
        }
        request.setAttribute(RESERVATIONS, reservations);
        int reservationCount = reservationService.getReservationCount(user.getId());
        int pageNumber = reservationCount / RECORDS_PER_PAGE;
        if (pageNumber % RECORDS_PER_PAGE > 0 && !(reservationCount % RECORDS_PER_PAGE == 0)) {
            pageNumber++;
        }
        request.setAttribute(PAGE_NUMBER, pageNumber);
        request.setAttribute(CURRENT_PAGE, currentPage);
        return CommandResult.forward(MY_RESERVATIONS_PAGE);
    }
}
