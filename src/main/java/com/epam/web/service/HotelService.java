package com.epam.web.service;

import com.epam.web.dao.DaoHelper;
import com.epam.web.dao.DaoHelperFactory;
import com.epam.web.dao.HotelDao;
import com.epam.web.entitiy.Hotel;
import com.epam.web.exception.DaoException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class HotelService {

    private final DaoHelperFactory factory;

    public HotelService(DaoHelperFactory factory) {
        this.factory = factory;
    }

    public List<Hotel> getAllHotels() {
        List<Hotel> hotels = new ArrayList<>();
        try(DaoHelper helper = factory.createDaoHelper()) {
            HotelDao dao = helper.createHotelDao();
            List<Hotel> retrievedHotels = dao.getAllHotels();
            hotels.addAll(retrievedHotels);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return hotels;
    }

    public void addHotel(Hotel hotel) {
        try(DaoHelper helper = factory.createDaoHelper()) {
            HotelDao dao = helper.createHotelDao();
            dao.addHotel(hotel);
        } catch (SQLException | DaoException e) {
            e.printStackTrace();
        }
    }

    public long getHotelIdByName(String hotelName) {
        Optional<Hotel> hotel = null;
        try(DaoHelper helper = factory.createDaoHelper()) {
            HotelDao dao = helper.createHotelDao();
            hotel = dao.getHotelIdByName(hotelName);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return hotel.get().getId();
    }
}
