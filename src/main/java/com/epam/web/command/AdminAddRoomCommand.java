package com.epam.web.command;

import com.epam.web.entitiy.Room;
import com.epam.web.exception.ServiceException;
import com.epam.web.extractor.RoomExtractor;
import com.epam.web.service.HotelService;
import com.epam.web.service.RoomService;
import com.epam.web.validator.RoomValidator;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AdminAddRoomCommand implements Command {

    private final static String ADD_ROOM_PAGE = "controller?command=adminShowAddRoomPage";

    private final RoomService roomService;
    private final HotelService hotelService;
    private final RoomValidator validator;
    private final RoomExtractor extractor;

    public AdminAddRoomCommand(RoomService roomService, HotelService hotelService, RoomValidator validator, RoomExtractor extractor) {
        this.roomService = roomService;
        this.hotelService = hotelService;
        this.validator = validator;
        this.extractor = extractor;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        Room extractedRoom = extractor.extract(request);
        if (!validator.validate(extractedRoom)) {
            request.setAttribute("error", "incorrectData");
            return CommandResult.forward("/WEB-INF/view/admin/adminaddroom.jsp");
        }
        roomService.addRoom(extractedRoom);
        return CommandResult.redirect(ADD_ROOM_PAGE);
    }

}
