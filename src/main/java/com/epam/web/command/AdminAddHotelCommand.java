package com.epam.web.command;

import com.epam.web.entitiy.Hotel;
import com.epam.web.service.HotelService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AdminAddHotelCommand implements Command {

    private final HotelService service;

    public AdminAddHotelCommand(HotelService service) {
        this.service = service;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        String hotelName = request.getParameter("hotelName");
        String description = request.getParameter("description");
        long photoId = Long.parseLong(request.getParameter("photoId"));
        Hotel hotel = new Hotel(hotelName, description, photoId);
        service.addHotel(hotel);
        return CommandResult.forward("/WEB-INF/view/admin/adminsuccessfulpage.jsp");
    }

}
