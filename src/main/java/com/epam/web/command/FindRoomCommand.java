package com.epam.web.command;

import com.epam.web.entitiy.Room;
import com.epam.web.exception.ServiceException;
import com.epam.web.extractor.RoomExtractor;
import com.epam.web.service.RoomService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class FindRoomCommand implements Command {

    private final static String ORDER_ID = "orderId";
    private final static String USER_ID = "userId";
    private final static String ROOMS = "rooms";
    private final static String FIND_ROOM_PAGE = "/WEB-INF/view/admin/adminfindroompage.jsp";

    private final RoomService roomService;
    private final RoomExtractor extractor;

    public FindRoomCommand(RoomService roomService, RoomExtractor extractor) {
        this.roomService = roomService;
        this.extractor = extractor;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        request.setAttribute(ORDER_ID, request.getParameter(ORDER_ID));
        request.setAttribute(USER_ID, request.getParameter(USER_ID));

        Room extractedRoom = extractor.extract(request);
        List<Room> rooms = roomService.findRoom(extractedRoom);

        request.setAttribute(ROOMS, rooms);
        return CommandResult.forward(FIND_ROOM_PAGE);
    }

}
