package com.epam.web.command;

import com.epam.web.entitiy.Hotel;
import com.epam.web.exception.ServiceException;
import com.epam.web.service.HotelService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class BookingCommand implements Command {

    private final static String HOTELS = "hotels";
    private final static String BOOKING_PAGE = "/WEB-INF/view/booking.jsp";
    private final static String HOTEL_NAME = "hotelName";
    private final static String FALSE = "false";
    private final static String BOOKING_MESSAGE_PARAMETER = "showMes";
    private final static String BOOKING_ATTRIBUTE = "bookedSuccessfully";

    private final HotelService service;

    public BookingCommand(HotelService service) {
        this.service = service;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        HttpSession session = request.getSession();

        if (FALSE.equals(request.getParameter(BOOKING_MESSAGE_PARAMETER))) {
            session.removeAttribute(BOOKING_ATTRIBUTE);
        }

        String hotelName = request.getParameter(HOTEL_NAME);
        List<Hotel> hotels = service.getAllHotels();

        request.setAttribute(HOTELS, hotels);
        request.setAttribute(HOTEL_NAME, hotelName);

        return CommandResult.forward(BOOKING_PAGE);
    }

}