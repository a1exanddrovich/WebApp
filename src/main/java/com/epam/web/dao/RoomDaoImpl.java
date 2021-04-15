package com.epam.web.dao;

import com.epam.web.connection.ProxyConnection;
import com.epam.web.entitiy.Room;
import com.epam.web.exception.DaoException;
import com.epam.web.mapper.RoomMapper;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class RoomDaoImpl extends AbstractDao<Room> implements RoomDao {

    private static final String TABLE = "room";
    private final static String ADD_ROOM = "INSERT INTO room (hotel_id, class, places) VALUES (?, ?, ?)";
    private final static String FIND_PROPER_ROOM = "SELECT * FROM room WHERE hotel_id = ? AND class = ? AND places = ? AND (booked_until < ? OR booked_until IS NULL)";
    private final static String FIND_ROOM_BY_ID = "SELECT * FROM room WHERE id = ";
    private final static String BOOK_ROOM_UNTIL = "UPDATE room SET booked_until = ?, booked_from = ? WHERE id = ?";
    private final static String UNBOOK_ROOM = "UPDATE room SET booked_until = null, booked_from = null WHERE id = ";

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

    @Override
    public List<Room> findRoom(Room room) throws SQLException {
        long hotelId = room.getHotelId();
        String roomClass = room.getRoomClass();
        int places = room.getPlaceCount();
        Date bookedFrom = room.getBookedFrom();
        return executeQuery(FIND_PROPER_ROOM, hotelId, roomClass, places, bookedFrom);
    }

    @Override
    public Optional<Room> findRoomById(long roomId) throws SQLException {
        return executeForSingleResult(FIND_ROOM_BY_ID + roomId);
    }

    @Override
    public void updateRoom(Room room, Date arrivalDate, Date departureDate) throws DaoException {
        executeUpdate(BOOK_ROOM_UNTIL, departureDate, arrivalDate, room.getId());
    }

    @Override
    public void unbookRoomById(long roomId) throws DaoException {
        executeUpdate(UNBOOK_ROOM + roomId);
    }

}
