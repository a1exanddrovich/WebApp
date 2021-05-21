package com.epam.web.command;

import com.epam.web.entitiy.Room;
import com.epam.web.entitiy.RoomClass;
import com.epam.web.service.HotelService;
import com.epam.web.service.RoomService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AdminAddRoomCommand implements Command {

    private final static String HOTEL_NAME = "hotelName";
    private final static String ROOM_CLASS = "class";
    private final static String PLACES = "places";

    private final RoomService roomService;
    private final HotelService hotelService;

    public AdminAddRoomCommand(RoomService roomService, HotelService hotelService) {
        this.roomService = roomService;
        this.hotelService = hotelService;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        String hotelName = request.getParameter(HOTEL_NAME);
        RoomClass roomClass = RoomClass.valueOf(request.getParameter(ROOM_CLASS));
        int places = Integer.parseInt(request.getParameter(PLACES));
        long hotelId = hotelService.getHotelIdByName(hotelName);
        Room room = new Room(0, hotelId, roomClass, places, null, null);
        roomService.addRoom(room);
        return CommandResult.redirect("controller?command=adminShowAddRoomPage");
    }

}
