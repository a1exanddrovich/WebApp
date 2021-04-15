package com.epam.web.mapper;

import com.epam.web.entitiy.Hotel;
import java.sql.ResultSet;
import java.sql.SQLException;

public class HotelMapper implements Mapper{

    private static final String ID = "id";
    private static final String NAME = "name";
    private static final String DESCRIPTION = "description";
    private static final String IMAGE_ID = "image_id";
    private static final String BALANCE = "balance";

    @Override
    public Hotel map(ResultSet resultSet) throws SQLException {
        long id = resultSet.getLong(ID);
        String name = resultSet.getString(NAME);
        String description = resultSet.getString(DESCRIPTION);
        long imageId = resultSet.getLong(IMAGE_ID);
        Hotel hotel = new Hotel(id, name, description, imageId);
        return hotel;
    }

}
