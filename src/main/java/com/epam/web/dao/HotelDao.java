package com.epam.web.dao;

import com.epam.web.entitiy.Hotel;
import com.epam.web.exception.DaoException;
import java.util.List;
import java.util.Optional;

public interface HotelDao extends Dao<Hotel> {

    List<Hotel> getAllHotels(int currentPage, int recordsPerPage) throws DaoException;

    Optional<Hotel> getHotelIdByName(String hotel) throws DaoException;

    int countHotels() throws DaoException;

}
