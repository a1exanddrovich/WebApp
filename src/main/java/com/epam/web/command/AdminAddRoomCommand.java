package com.epam.web.command;

import com.epam.web.entitiy.Room;
import com.epam.web.entitiy.RoomClass;
import com.epam.web.exception.ServiceException;
import com.epam.web.service.HotelService;
import com.epam.web.service.RoomService;
import com.epam.web.validator.RoomValidator;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AdminAddRoomCommand implements Command {

    private final static String HOTEL_NAME = "hotelName";
    private final static String ROOM_CLASS = "class";
    private final static String PLACES = "places";
    private final static String ADD_ROOM_PAGE = "controller?command=adminShowAddRoomPage";

    private final RoomService roomService;
    private final HotelService hotelService;
    private final RoomValidator validator;

    public AdminAddRoomCommand(RoomService roomService, HotelService hotelService, RoomValidator validator) {
        this.roomService = roomService;
        this.hotelService = hotelService;
        this.validator = validator;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        String hotelName = request.getParameter(HOTEL_NAME);
        RoomClass roomClass = RoomClass.valueOf(request.getParameter(ROOM_CLASS));
        int places = 0;
        try {
            places = Integer.parseInt(request.getParameter(PLACES));
        } catch (NumberFormatException e) {
            request.setAttribute("invalidPlaceCount", "incorrectData");
            return CommandResult.forward("/WEB-INF/view/adminaddroom.jsp");
        }
        long hotelId = hotelService.getHotelIdByName(hotelName);
        Room room = new Room(0, hotelId, roomClass, places, null, null);
        if (!validator.validate(room)) {
            request.setAttribute("error", "incorrectData");
            return CommandResult.forward("/WEB-INF/view/admin/adminaddroom.jsp");
        }
        roomService.addRoom(room);
        return CommandResult.redirect(ADD_ROOM_PAGE);
    }

}
