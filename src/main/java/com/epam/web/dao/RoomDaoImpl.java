package com.epam.web.dao;

import com.epam.web.connection.ProxyConnection;
import com.epam.web.entitiy.Room;
import com.epam.web.exception.DaoException;
import com.epam.web.mapper.RoomMapper;

public class RoomDaoImpl extends AbstractDao<Room> implements RoomDao {

    private static final String TABLE = "room";
    private final static String ADD_ROOM = "INSERT INTO room (hotel_id, class, places) VALUES (?, ?, ?)";

    protected RoomDaoImpl(ProxyConnection connection) {
        super(connection, new RoomMapper(), TABLE);
    }

    @Override
    public void addRoom(Room room) throws DaoException {
        long hotelId = room.getHotelId();
        String roomClass = room.getRoomClass();
        int places = room.getPlaceCount();
        executeUpdate(ADD_ROOM, hotelId, roomClass, places);
    }

}
