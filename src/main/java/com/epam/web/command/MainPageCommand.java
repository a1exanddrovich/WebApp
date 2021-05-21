package com.epam.web.command;

import com.epam.web.entitiy.Hotel;
import com.epam.web.service.HotelService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class MainPageCommand implements Command {

    private final static int RECORDS_PER_PAGE = 9;

    private final HotelService service;

    public MainPageCommand(HotelService service) {
        this.service = service;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        int currentPage = Integer.parseInt(request.getParameter("currentPage"));
        List<Hotel> hotels = service.getAllHotels(currentPage, RECORDS_PER_PAGE);
        request.setAttribute("hotels", hotels);
        int hotelCount = service.getHotelCount();
        int pageNumber = hotelCount / RECORDS_PER_PAGE;
        if (pageNumber % RECORDS_PER_PAGE > 0 & !(hotelCount % RECORDS_PER_PAGE == 0)) {
            pageNumber++;
        }
        request.setAttribute("pageNumber", pageNumber);
        request.setAttribute("currentPage", currentPage);
        return CommandResult.forward("/WEB-INF/view/main.jsp");
    }

}
