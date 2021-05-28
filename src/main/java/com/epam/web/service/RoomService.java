package com.epam.web.service;

import com.epam.web.dao.DaoHelper;
import com.epam.web.dao.DaoHelperFactory;
import com.epam.web.dao.RoomDao;
import com.epam.web.entitiy.Room;
import com.epam.web.exception.DaoException;
import com.epam.web.exception.ServiceException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RoomService {

    private final DaoHelperFactory factory;

    public RoomService(DaoHelperFactory factory) {
        this.factory = factory;
    }

    public void addRoom(Room room) throws ServiceException {
        try(DaoHelper helper = factory.createDaoHelper()) {
            RoomDao dao = helper.createRoomDao();
            dao.save(room);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    public List<Room> findRoom(Room room) throws ServiceException {
        List<Room> rooms = new ArrayList<>();
        try (DaoHelper helper = factory.createDaoHelper()) {
            RoomDao dao = helper.createRoomDao();
            List<Room> retrievedRooms = dao.findProperRoom(room);
            rooms.addAll(retrievedRooms);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
        return rooms;
    }

    public Room findRoomById(long roomId) throws ServiceException {
        Optional<Room> room;
        try (DaoHelper helper = factory.createDaoHelper()) {
            RoomDao dao = helper.createRoomDao();
            room = dao.findById(roomId);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
        return room.get();
    }
}
