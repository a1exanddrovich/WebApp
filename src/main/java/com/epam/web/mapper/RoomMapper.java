package com.epam.web.mapper;

import com.epam.web.entitiy.Room;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class RoomMapper implements Mapper<Room> {

    private static final String ID = "id";
    private static final String HOTEL_ID = "hotel_id";
    private static final String CLASS = "class";
    private static final String PLACES = "places";
    private static final String BOOKED_UNTIL = "booked_until";
    private static final String BOOKED_FROM = "booked_from";

    @Override
    public Room map(ResultSet resultSet) throws SQLException {
        long id = resultSet.getLong(ID);
        long hotelId = resultSet.getLong(HOTEL_ID);
        String roomClass = resultSet.getString(CLASS);
        int places = resultSet.getInt(PLACES);
        Date bookedUntil = resultSet.getDate(BOOKED_UNTIL);
        Date bookedFrom = resultSet.getDate(BOOKED_FROM);
        Room room = new Room(id, hotelId, roomClass, places, bookedUntil, bookedFrom);
        return room;
    }

}
