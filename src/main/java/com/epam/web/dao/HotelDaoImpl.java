package com.epam.web.dao;

import com.epam.web.connection.ProxyConnection;
import com.epam.web.entitiy.Hotel;
import com.epam.web.exception.DaoException;
import com.epam.web.mapper.HotelMapper;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class HotelDaoImpl extends AbstractDao<Hotel> implements HotelDao {

    private static final String TABLE = "hotel";
    private final static String GET_ALL_HOTELS = "SELECT * FROM hotel";
    private final static String ADD_HOTEL = "INSERT INTO hotel (name, description, image_id) VALUES (?, ?, ?)";
    private final static String FIND_HOTEL_ID_BY_NAME = "SELECT * FROM hotel WHERE name = ?";
    private final static String TOP_UP_BALANCE = "UPDATE hotel SET balance = ? WHERE id = ?";
    private final static String GET_BALANCE = "SELECT * FROM hotel WHERE id = ?";

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

    @Override
    public Optional<Hotel> getHotelIdByName(String hotel) throws SQLException {
        return executeForSingleResult(FIND_HOTEL_ID_BY_NAME, hotel);
    }

    @Override
    public double getHotelBalance(long id) throws SQLException {
        Optional<Hotel> optionalHotel = executeForSingleResult(GET_BALANCE, id);
        Hotel hotel = optionalHotel.get();
        return hotel.getBalance();
    }

    @Override
    public void topUpBalance(double price, long hotelId) throws DaoException {
        try {
            price += getHotelBalance(hotelId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        executeUpdate(TOP_UP_BALANCE, price, hotelId);
    }

}
