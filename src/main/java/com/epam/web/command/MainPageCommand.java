package com.epam.web.command;

import com.epam.web.entitiy.Hotel;
import com.epam.web.exception.ServiceException;
import com.epam.web.service.HotelService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class MainPageCommand implements Command {

    private final static int RECORDS_PER_PAGE = 9;
    private final static String PAGE_NUMBER = "pageNumber";
    private final static String CURRENT_PAGE = "currentPage";
    private final static String MAIN_PAGE = "/WEB-INF/view/main.jsp";
    private final static String HOTELS = "hotels";
    private final static String FALSE = "false";
    private final static String SIGN_UP_MESSAGE_PARAMETER = "showMes";
    private final static String SIGN_UP_ATTRIBUTE = "registeredSuccessfully";

    private final HotelService service;

    public MainPageCommand(HotelService service) {
        this.service = service;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        HttpSession session = request.getSession();

        if(FALSE.equals(request.getParameter(SIGN_UP_MESSAGE_PARAMETER))) {
            session.removeAttribute(SIGN_UP_ATTRIBUTE);
        }

        int currentPage = Integer.parseInt(request.getParameter(CURRENT_PAGE));
        List<Hotel> hotels = service.getAllHotels(currentPage, RECORDS_PER_PAGE);

        request.setAttribute(HOTELS, hotels);

        int hotelCount = service.getHotelCount();
        int pageNumber = hotelCount / RECORDS_PER_PAGE;

        if (pageNumber % RECORDS_PER_PAGE > 0 & !(hotelCount % RECORDS_PER_PAGE == 0)) {
            pageNumber++;
        }

        request.setAttribute(PAGE_NUMBER, pageNumber);
        request.setAttribute(CURRENT_PAGE, currentPage);

        return CommandResult.forward(MAIN_PAGE);
    }

}
