package com.epam.web.service;

import com.epam.web.dao.DaoHelper;
import com.epam.web.dao.DaoHelperFactory;
import com.epam.web.dao.RoomDao;
import com.epam.web.entitiy.Room;
import com.epam.web.exception.DaoException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RoomService {

    private final DaoHelperFactory factory;

    public RoomService(DaoHelperFactory factory) {
        this.factory = factory;
    }

    public void addRoom(Room room) {
        try(DaoHelper helper = factory.createDaoHelper()) {
            RoomDao dao = helper.createRoomDao();
            dao.save(room);
        } catch (DaoException e) {
            throw new SecurityException(e);
        }
    }

    public List<Room> findRoom(Room room) {
        List<Room> rooms = new ArrayList<>();
        try (DaoHelper helper = factory.createDaoHelper()) {
            RoomDao dao = helper.createRoomDao();
            List<Room> retrievedRooms = dao.findProperRoom(room);
            rooms.addAll(retrievedRooms);
        } catch (SQLException | DaoException e) {
            throw new SecurityException(e);
        }
        return rooms;
    }

    public Room findRoomById(long roomId) {
        Optional<Room> room;
        try (DaoHelper helper = factory.createDaoHelper()) {
            RoomDao dao = helper.createRoomDao();
            room = dao.findRoomById(roomId);
        } catch (SQLException | DaoException e) {
            throw new SecurityException(e);
        }
        return room.get();
    }
}
