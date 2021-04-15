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
            dao.addRoom(room);
        } catch (SQLException | DaoException e) {
            e.printStackTrace();
        }
    }

    public List<Room> findRoom(Room room) {
        List<Room> rooms = new ArrayList<>();
        try (DaoHelper helper = factory.createDaoHelper()) {
            RoomDao dao = helper.createRoomDao();
            List<Room> retrievedRooms = dao.findRoom(room);
            rooms.addAll(retrievedRooms);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rooms;
    }

    public Room findRoomById(long roomId) {
        Optional<Room> room = null;
        try (DaoHelper helper = factory.createDaoHelper()) {
            RoomDao dao = helper.createRoomDao();
            room = dao.findRoomById(roomId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return room.get();
    }
}
