package com.epam.web.dao;

import com.epam.web.entitiy.Hotel;
import com.epam.web.exception.DaoException;

import java.sql.SQLException;
import java.util.List;

public interface HotelDao extends Dao<Hotel> {

    List<Hotel> getAllHotels() throws SQLException;

    void addHotel(Hotel hotel) throws DaoException;

}
