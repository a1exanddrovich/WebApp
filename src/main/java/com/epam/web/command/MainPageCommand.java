package com.epam.web.command;

import com.epam.web.entitiy.Hotel;
import com.epam.web.service.HotelService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class MainPageCommand implements Command {

    private final HotelService service;

    public MainPageCommand(HotelService service) {
        this.service = service;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        List<Hotel> hotels = service.getAllHotels();
        request.setAttribute("hotels", hotels);
        return CommandResult.forward("/WEB-INF/view/main.jsp");
    }

}
