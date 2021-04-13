package com.epam.web.command;

import com.epam.web.entitiy.Room;
import com.epam.web.service.RoomService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AdminAddRoomCommand implements Command {

    private final RoomService service;

    public AdminAddRoomCommand(RoomService service) {
        this.service = service;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        long hotelId = Long.parseLong(request.getParameter("hotelId"));
        String roomClass = request.getParameter("class");
        int places = Integer.parseInt(request.getParameter("places"));
        Room room = new Room(hotelId, roomClass, places);
        service.addRoom(room);
        return CommandResult.forward("/WEB-INF/view/admin/adminsuccessfulpage.jsp");
    }

}
