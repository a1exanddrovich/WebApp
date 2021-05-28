package com.epam.web.command;

import com.epam.web.entitiy.Room;
import com.epam.web.entitiy.RoomClass;
import com.epam.web.exception.ServiceException;
import com.epam.web.service.HotelService;
import com.epam.web.service.RoomService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class FindRoomCommand implements Command {

    private final static String DATE_FORMAT = "yyyy-MM-dd";

    private final DateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
    private final RoomService roomService;
    private final HotelService hotelService;

    public FindRoomCommand(RoomService roomService, HotelService hotelService) {
        this.roomService = roomService;
        this.hotelService = hotelService;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        request.setAttribute("orderId", request.getParameter("orderId"));
        request.setAttribute("userId", request.getParameter("userId"));
        String hotel = request.getParameter("hotel");
        RoomClass roomClass = RoomClass.valueOf(request.getParameter("class"));
        int places = Integer.parseInt(request.getParameter("places"));
        Date arrival = null;
        Date departure = null;
        try {
            arrival = dateFormat.parse(request.getParameter("arrival"));
            departure = dateFormat.parse(request.getParameter("departure"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        long hotelId = hotelService.getHotelIdByName(hotel);
        Room room = new Room(0, hotelId, roomClass, places, arrival, departure);
        List<Room> rooms = roomService.findRoom(room);
        request.setAttribute("rooms", rooms);
        return CommandResult.forward("/WEB-INF/view/admin/adminfindroompage.jsp");
    }

}
