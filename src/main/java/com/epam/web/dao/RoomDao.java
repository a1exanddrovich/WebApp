package com.epam.web.dao;

import com.epam.web.entitiy.Room;
import com.epam.web.exception.DaoException;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface RoomDao extends Dao<Room> {

//    void addRoom(Room room) throws DaoException;

    List<Room> findProperRoom(Room room) throws SQLException, DaoException;

    Optional<Room> findRoomById(long roomId) throws SQLException, DaoException;

    void updateRoom(Room room, Date arrivalDate, Date departureDate) throws DaoException;
//
//    void unbookRoomById(long roomId) throws DaoException;
}
