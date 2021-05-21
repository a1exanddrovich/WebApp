package com.epam.web.dao;

import com.epam.web.entitiy.Hotel;
import com.epam.web.exception.DaoException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface HotelDao extends Dao<Hotel> {

    List<Hotel> getAllHotels(int currentPage, int recordsPerPage) throws SQLException, DaoException;

    Optional<Hotel> getHotelIdByName(String hotel) throws SQLException, DaoException;

//    BigDecimal getHotelBalance(long id) throws SQLException, DaoException;
//
//    void topUpBalance(Hotel hotel) throws DaoException;

    int countHotels() throws SQLException, DaoException;

    Optional<Hotel> findHotelById(long hotelId) throws DaoException;
}
