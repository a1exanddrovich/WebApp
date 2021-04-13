package com.epam.web.dao;

import com.epam.web.connection.ProxyConnection;
import com.epam.web.entitiy.Hotel;
import com.epam.web.exception.DaoException;
import com.epam.web.mapper.HotelMapper;
import java.sql.SQLException;
import java.util.List;

public class HotelDaoImpl extends AbstractDao<Hotel> implements HotelDao {

    private static final String TABLE = "hotel";
    private final static String GET_ALL_HOTELS = "SELECT * FROM hotel";
    private final static String ADD_HOTEL = "INSERT INTO hotel (name, description, image_id) VALUES (?, ?, ?)";

    public HotelDaoImpl(ProxyConnection connection) {
        super(connection, new HotelMapper(), TABLE);
    }

    @Override
    public List<Hotel> getAllHotels() throws SQLException {
        return executeQuery(GET_ALL_HOTELS);
    }

    @Override
    public void addHotel(Hotel hotel) throws DaoException {
        String name = hotel.getName();
        String description = hotel.getDescription();
        long photoId = hotel.getImageId();
        executeUpdate(ADD_HOTEL, name, description, photoId);
    }

}
