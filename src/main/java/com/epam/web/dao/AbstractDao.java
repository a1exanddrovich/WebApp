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

    private final ProxyConnection connection;
    private final Mapper<T> mapper;
    private final String tableName;

    protected AbstractDao(ProxyConnection connection, Mapper<T> mapper, String tableName) {
        this.connection = connection;
        this.mapper = mapper;
        this.tableName = tableName;
    }

    protected List<T> executeQuery(String query, Object... params) throws SQLException {
        List<T> entities = new ArrayList<>();
        PreparedStatement statement = this.createStatement(query, params);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            T entity = mapper.map(resultSet);
            entities.add(entity);
        }
        return entities;
    }

    protected Optional<T> executeForSingleResult(String query, Object... params) throws SQLException {
        List<T> users = executeQuery(query, params);
        if (users.size() == 1) {
            return Optional.of(users.get(0));
        } else if (users.size() > 1) {
            throw new IllegalArgumentException("More than one record found");
        } else {
            return Optional.empty();
        }
    }

    protected void executeUpdate(String query, Object... params) throws DaoException {
        try(PreparedStatement statement = createStatement(query, params)) {
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException();
        }
    }

    private PreparedStatement createStatement(String query, Object... params) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(query);
        for (int i = 1; i < params.length + 1; i++) {
            statement.setObject(i, params[i - 1]);
        }
        return statement;
    }


}
