package com.epam.web.dao;

import com.epam.web.connection.ProxyConnection;
import com.epam.web.entitiy.Room;
import com.epam.web.exception.DaoException;
import com.epam.web.mapper.RoomMapper;
import java.util.List;
import java.util.Optional;

public class RoomDaoImpl extends AbstractDao<Room> implements RoomDao {

    private static final String TABLE = "room";
    private static final String CREATE = "INSERT INTO room (hotel_id, class, places) VALUES (?, ?, ?)";
    private static final String UPDATE = "UPDATE room SET hotel_id = ?, class = ?, places = ?, booked_until = ?, booked_from = ? WHERE id = ?";
    private final static String FIND_PROPER_ROOM = "SELECT * FROM room WHERE hotel_id = ? AND class = ? AND places = ? AND booked_until IS NULL";
    private final static String INVALID_ROOM_ID = "Room has not been found. Id is invalid: ";

    protected RoomDaoImpl(ProxyConnection connection) {
        super(connection, new RoomMapper(), TABLE);
    }


    @Override
    protected void create(Room room) throws DaoException {
        executeUpdate(CREATE, room.getHotelId(), room.getRoomClass().toString(), room.getPlaceCount());
    }

    @Override
    protected void update(Room room) throws DaoException {
        Optional<Room> optionalRoom = findById(room.getId());
        if (optionalRoom.isEmpty()) {
            throw new DaoException(INVALID_ROOM_ID + room.getId());
        }
        executeUpdate(UPDATE, room.getHotelId(), room.getRoomClass().toString(), room.getPlaceCount(), room.getBookedUntil(), room.getBookedFrom(), room.getId());
    }

    @Override
    public List<Room> findProperRoom(Room room) throws DaoException {
        long hotelId = room.getHotelId();
        String roomClass = room.getRoomClass().toString();
        int places = room.getPlaceCount();
        return executeQuery(FIND_PROPER_ROOM, hotelId, roomClass, places);
    }

}
