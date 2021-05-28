package com.epam.web.dao;

import com.epam.web.entitiy.Identifiable;
import com.epam.web.exception.DaoException;
import java.util.List;
import java.util.Optional;


/**
 * This interface represents Data Access Object pattern as a part of MVC pattern and provides general
 * operations for access database. Based on the data accessed from {@link java.sql.ResultSet} objects are
 * mapped via {@link com.epam.web.mapper.Mapper}
 *
 * @author Alexey Bliznichenko
 * @param <T> has to implement {@link Identifiable} interface
 * @see Identifiable
 * @see DaoException
 */
public interface Dao<T extends Identifiable> {

    /**
     *  Returns an Optional object contains an {@link Identifiable} object found in the database. If nothing has been found then
     *  an empty object would have been returned
     *
     * @param id {@link Identifiable}
     * @return Optional of {@link Identifiable} object
     * @throws DaoException {@link DaoException} Exceptions on the DAO layer
     */
    Optional<T> findById(long id) throws DaoException;

    /**
     * Returns a list of {@link Identifiable} objects mapped from all the records from the database
     *
     * @return List of {@link Identifiable} objects
     * @throws DaoException {@link DaoException} Exceptions on the DAO layer
     */
    List<T> findAll() throws DaoException;

    /**
     * Deletes a record in the database by id of an {@link Identifiable} object
     *
     * @param id {@link Identifiable}
     * @throws DaoException {@link DaoException} Exceptions on the DAO layer
     */
    void deleteById(long id) throws DaoException;

    /**
     * Inserts a new record in the table if the id of the given entity does not exist int the table or update an existing
     * record if it the given id exists int the table
     *
     * @param entity {@link Identifiable} object
     * @throws DaoException {@link DaoException} Exceptions on the DAO layer
     */
    void save(T entity) throws DaoException;

}
