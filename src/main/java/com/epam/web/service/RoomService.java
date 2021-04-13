package com.epam.web.service;

import com.epam.web.dao.DaoHelper;
import com.epam.web.dao.DaoHelperFactory;
import com.epam.web.dao.RoomDao;
import com.epam.web.entitiy.Room;
import com.epam.web.exception.DaoException;
import java.sql.SQLException;

public class RoomService {

    private final DaoHelperFactory factory;

    public RoomService(DaoHelperFactory factory) {
        this.factory = factory;
    }

    public void addRoom(Room room) {
        try(DaoHelper helper = factory.createDaoHelper()) {
            RoomDao dao = helper.createRoomDao();
            dao.addRoom(room);
        } catch (SQLException | DaoException e) {
            e.printStackTrace();
        }
    }

}
