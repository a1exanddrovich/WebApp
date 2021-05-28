package com.epam.web.dao;

import com.epam.web.connection.ProxyConnection;
import com.epam.web.entitiy.Hotel;
import com.epam.web.exception.DaoException;
import com.epam.web.mapper.HotelMapper;
import java.util.List;
import java.util.Optional;

public class HotelDaoImpl extends AbstractDao<Hotel> implements HotelDao {

    private static final String TABLE = "hotel";
    private final static String GET_ALL_HOTELS_LIMIT = "SELECT * FROM hotel LIMIT ?, ?";
    private final static String CREATE = "INSERT INTO hotel (name, description, image_path) VALUES (?, ?, ?)";
    private final static String UPDATE = "UPDATE hotel SET name = ?, description = ?, image_path = ?, balance = ? WHERE id = ?";
    private final static String FIND_HOTEL_ID_BY_NAME = "SELECT * FROM hotel WHERE name = ?";
    private final static String GET_COUNT = "SELECT COUNT(*) FROM hotel";

    public HotelDaoImpl(ProxyConnection connection) {
        super(connection, new HotelMapper(), TABLE);
    }

    @Override
    public List<Hotel> getAllHotels(int currentPage, int recordsPerPage) throws DaoException {
        return executeQuery(GET_ALL_HOTELS_LIMIT, (currentPage - 1) * recordsPerPage, recordsPerPage);
    }


    @Override
    protected void create(Hotel hotel) throws DaoException {
        String name = hotel.getName();
        String description = hotel.getDescription();
        String photoId = hotel.getImagePath();
        executeUpdate(CREATE, name, description, photoId);
    }

    @Override
    protected void update(Hotel hotel) throws DaoException {
        Optional<Hotel> optionalHotel = findById(hotel.getId());
        if (optionalHotel.isEmpty()) {
            throw new DaoException("Hotel has not been found. Id is invalid: " + hotel.getId());
        }
        executeUpdate(UPDATE, hotel.getName(), hotel.getDescription(), hotel.getImagePath(), hotel.getBalance(), hotel.getId());
    }

    @Override
    public Optional<Hotel> getHotelIdByName(String hotel) throws DaoException {
        return executeForSingleResult(FIND_HOTEL_ID_BY_NAME, hotel);
    }

    @Override
    public int countHotels() throws DaoException {
        return getCount(GET_COUNT);
    }

}
