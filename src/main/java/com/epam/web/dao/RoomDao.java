package com.epam.web.dao;

import com.epam.web.entitiy.Room;
import com.epam.web.exception.DaoException;
import java.util.List;

public interface RoomDao extends Dao<Room> {

    List<Room> findProperRoom(Room room) throws DaoException;

}
