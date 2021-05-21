package com.epam.web.dao;

import com.epam.web.entitiy.Identifiable;
import com.epam.web.exception.DaoException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface Dao<T extends Identifiable> {

    Optional<T> findById(long id) throws DaoException, SQLException;

    List<T> findAll() throws DaoException;

    void deleteById(long id) throws DaoException;

    void save(T entity) throws DaoException;

}
