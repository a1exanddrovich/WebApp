package com.epam.web.mapper;

import com.epam.web.entitiy.Identifiable;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * This interface is implemented by classes which create objects on the base of the data obtained from {@link ResultSet}
 *
 * @author Alexey Bliznichenko
 * @param <T> has to implement {@link Identifiable} interface
 */
public interface Mapper<T extends Identifiable> {

    /**
     * Creates an object on the base of the data obtained from {@link ResultSet}
     *
     * @param resultSet {@link ResultSet}
     * @return Identifiable {@link Identifiable}
     * @throws SQLException {@link SQLException} Exceptions caused by ResultSet behaviour
     */
    T map(ResultSet resultSet) throws SQLException;

}
