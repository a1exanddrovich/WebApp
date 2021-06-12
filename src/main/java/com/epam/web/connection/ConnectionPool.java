package com.epam.web.connection;

import com.epam.web.exception.ConnectionPoolException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.ReentrantLock;

public class ConnectionPool {

    private final static Logger LOGGER = LogManager.getLogger(ConnectionPool.class);
    private final static AtomicReference<ConnectionPool> INSTANCE = new AtomicReference<>();
    private final static int POOL_SIZE = 10;
    private final static ReentrantLock INSTANCE_LOCK = new ReentrantLock();

    private final Queue<ProxyConnection> availableConnections;
    private final Queue<ProxyConnection> connectionsInUse;
    private final ConnectionFactory factory;
    private final ReentrantLock connectionsLock;
    private final Semaphore semaphore;

    private ConnectionPool() {
        factory = new ConnectionFactory();
        this.semaphore = new Semaphore(POOL_SIZE, true);
        this.availableConnections = new ArrayDeque<>();
        this.connectionsInUse = new ArrayDeque<>();
        this.connectionsLock = new ReentrantLock();
        establishConnections();
    }

    public static ConnectionPool getInstance() {
        if (INSTANCE.get() == null) {
            try {
                INSTANCE_LOCK.lock();
                if (INSTANCE.get() == null) {
                    ConnectionPool pool = new ConnectionPool();
                    INSTANCE.getAndSet(pool);
                }
            } finally {
                INSTANCE_LOCK.unlock();
            }
        }
        return INSTANCE.get();
    }

    private void establishConnections() {
        try {
            for (int i = 0; i < POOL_SIZE; i++) {
                Connection connection = factory.establishConnection();
                ProxyConnection proxyConnection = new ProxyConnection(connection, this);
                availableConnections.add(proxyConnection);
            }
            LOGGER.info("Pool created");
        } catch (SQLException throwables) {
            LOGGER.fatal(throwables.getMessage());
            throw new ConnectionPoolException(throwables.getMessage(), throwables);
        }
    }

    public void returnConnection(ProxyConnection connection) {
        connectionsLock.lock();
        try {
            if (this.connectionsInUse.contains(connection)) {
                availableConnections.offer(connection);
                connectionsInUse.remove(connection);
            }
        } finally {
            semaphore.release();
            connectionsLock.unlock();
        }

    }

    public ProxyConnection getConnection() {
        try {
            semaphore.acquire();
            connectionsLock.lock();
            ProxyConnection connection = this.availableConnections.remove();
            this.connectionsInUse.add(connection);
            return connection;
        } catch (InterruptedException e) {
            LOGGER.fatal(e.getMessage());
            throw new ConnectionPoolException(e.getMessage(), e);
        } finally {
            connectionsLock.unlock();
        }
    }

}
