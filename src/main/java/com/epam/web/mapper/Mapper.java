package com.epam.web.mapper;

import com.epam.web.entitiy.Identifiable;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface Mapper<T extends Identifiable> {

    T map(ResultSet resultSet) throws SQLException;

}
