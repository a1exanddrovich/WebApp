package com.epam.web.dao;

import com.epam.web.entitiy.Room;
import com.epam.web.exception.DaoException;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface RoomDao extends Dao<Room> {

    List<Room> findProperRoom(Room room) throws DaoException;

}
