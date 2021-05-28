package com.epam.web.dao;

import com.epam.web.connection.ProxyConnection;
import com.epam.web.entitiy.Identifiable;
import com.epam.web.exception.DaoException;
import com.epam.web.mapper.Mapper;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AbstractDao<T extends Identifiable> implements Dao<T> {

    private static final String FIND_BY_ID = "SELECT * FROM %s WHERE id = ?";
    private static final String DELETE_BY_ID = "DELETE FROM %s WHERE id = ?";
    private static final String FIND_ALL = "SELECT * FROM %s";
    private static final String MULTIPLE_RECORDS_FOUND = "More than one record found";

    private final ProxyConnection connection;
    private final Mapper<T> mapper;
    private final String tableName;

    protected AbstractDao(ProxyConnection connection, Mapper<T> mapper, String tableName) {
        this.connection = connection;
        this.mapper = mapper;
        this.tableName = tableName;
    }

    protected List<T> executeQuery(String query, Object... params) throws DaoException {
        List<T> entities = new ArrayList<>();
        PreparedStatement statement = this.createStatement(query, params);
        ResultSet resultSet = null;
        try {
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                T entity = mapper.map(resultSet);
                entities.add(entity);
            }
        } catch (SQLException throwables) {
            throw new DaoException(throwables);
        }
        return entities;
    }

    protected Optional<T> executeForSingleResult(String query, Object... params) throws DaoException {
        List<T> users = executeQuery(query, params);
        if (users.size() == 1) {
            return Optional.of(users.get(0));
        } else if (users.size() > 1) {
            throw new IllegalArgumentException(MULTIPLE_RECORDS_FOUND);
        } else {
            return Optional.empty();
        }
    }

    protected void executeUpdate(String query, Object... params) throws DaoException {
        try(PreparedStatement statement = createStatement(query, params)) {
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    protected int getCount(String query) throws DaoException {
        PreparedStatement statement = null;
        int recordCount = 0;
        try {
            statement = this.createStatement(query);
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()) {
                recordCount = resultSet.getInt(1);
            }
        } catch (DaoException | SQLException e) {
            throw new DaoException(e);
        }
        return recordCount;
    }

    private PreparedStatement createStatement(String query, Object... params) throws DaoException {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(query);
            for (int i = 1; i < params.length + 1; i++) {
                statement.setObject(i, params[i - 1]);
            }
        } catch (SQLException throwables) {
            throw  new DaoException(throwables);
        }
        return statement;
    }


    @Override
    public Optional<T> findById(long id) throws DaoException {
        String query = String.format(FIND_BY_ID, tableName);
        return executeForSingleResult(query, id);
    }

    @Override
    public List<T> findAll() throws DaoException {
        String query = String.format(FIND_ALL, tableName);
        return executeQuery(query);
    }

    @Override
    public void deleteById(long id) throws DaoException {
        String query = String.format(DELETE_BY_ID, tableName);
        executeUpdate(query, id);
    }

    @Override
    public void save(T entity) throws DaoException {
        if (entity.getId() == 0) {
            create(entity);
        } else {
            update(entity);
        }
    }

    protected void create(T entity) throws DaoException { }

    protected void update(T entity) throws DaoException { }

}