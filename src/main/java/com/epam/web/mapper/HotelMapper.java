package com.epam.web.mapper;

import com.epam.web.entitiy.Hotel;
import java.sql.ResultSet;
import java.sql.SQLException;

public class HotelMapper implements Mapper{

    public static final String ID = "id";
    public static final String NAME = "name";
    public static final String DESCRIPTION = "description";

    @Override
    public Hotel map(ResultSet resultSet) throws SQLException {
        long id = resultSet.getLong(ID);
        String name = resultSet.getString(NAME);
        String description = resultSet.getString(DESCRIPTION);
        Hotel hotel = new Hotel(id, name, description);
        return hotel;
    }

}
