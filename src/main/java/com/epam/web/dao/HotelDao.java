package com.epam.web.dao;

import com.epam.web.entitiy.Hotel;

import java.sql.SQLException;
import java.util.List;

public interface HotelDao extends Dao<Hotel> {

    List<Hotel> getAllHotels() throws SQLException;

}
