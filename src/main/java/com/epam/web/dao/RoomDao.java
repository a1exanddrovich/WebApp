package com.epam.web.dao;

import com.epam.web.entitiy.Room;
import com.epam.web.exception.DaoException;

public interface RoomDao extends Dao<Room> {

    void addRoom(Room room) throws DaoException;

}
