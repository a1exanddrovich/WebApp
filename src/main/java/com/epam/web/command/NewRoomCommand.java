package com.epam.web.command;

import com.epam.web.entitiy.Hotel;
import com.epam.web.exception.ServiceException;
import com.epam.web.service.HotelService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class NewRoomCommand implements Command {

    private final static String HOTELS = "hotels";
    private final static String ADD_ROOM_PAGE = "/WEB-INF/view/admin/adminaddroom.jsp";

    private final HotelService service;

    public NewRoomCommand(HotelService service) {
        this.service = service;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        HttpSession session = request.getSession();
        if("false".equals(request.getParameter("showMes"))) {
            session.removeAttribute("roomAddedSuccessfully");
        }
        List<Hotel> hotels = service.getAllHotels();
        request.setAttribute(HOTELS, hotels);
        return CommandResult.forward(ADD_ROOM_PAGE);
    }

}
