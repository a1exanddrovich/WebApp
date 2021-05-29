package com.epam.web.command;

import com.epam.web.entitiy.Room;
import com.epam.web.exception.ServiceException;
import com.epam.web.extractor.RoomExtractor;
import com.epam.web.service.HotelService;
import com.epam.web.service.RoomService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class FindRoomCommand implements Command {

    private final RoomService roomService;
    private final HotelService hotelService;
    private final RoomExtractor extractor;

    public FindRoomCommand(RoomService roomService, HotelService hotelService, RoomExtractor extractor) {
        this.roomService = roomService;
        this.hotelService = hotelService;
        this.extractor = extractor;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        request.setAttribute("orderId", request.getParameter("orderId"));
        request.setAttribute("userId", request.getParameter("userId"));
        Room extractedRoom = extractor.extract(request);
        List<Room> rooms = roomService.findRoom(extractedRoom);
        request.setAttribute("rooms", rooms);
        return CommandResult.forward("/WEB-INF/view/admin/adminfindroompage.jsp");
    }

}
