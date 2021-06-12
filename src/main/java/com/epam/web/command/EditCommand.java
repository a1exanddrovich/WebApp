package com.epam.web.command;

import com.epam.web.entitiy.Hotel;
import com.epam.web.exception.ServiceException;
import com.epam.web.service.HotelService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class EditCommand implements Command {

    private final static String HOTELS = "hotels";
    private final static String EDIT_PAGE = "/WEB-INF/view/edit.jsp";
    private final static String ORDER_ID = "orderId";

    private final HotelService service;

    public EditCommand(HotelService service) {
        this.service = service;
    }
    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        if(request.getParameter(ORDER_ID) != null) {
            request.setAttribute(ORDER_ID, request.getParameter(ORDER_ID));
        }
        List<Hotel> hotels = service.getAllHotels();
        request.setAttribute(HOTELS, hotels);
        return CommandResult.forward(EDIT_PAGE);
    }
}
