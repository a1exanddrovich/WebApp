package com.epam.web.service;

import com.epam.web.dao.DaoHelper;
import com.epam.web.dao.DaoHelperFactory;
import com.epam.web.dao.HotelDao;
import com.epam.web.entitiy.Hotel;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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

}
