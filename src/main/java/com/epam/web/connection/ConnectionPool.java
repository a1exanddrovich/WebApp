package com.epam.web.connection;

import com.epam.web.exception.ConnectionPoolException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayDeque;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.ReentrantLock;

public class ConnectionPool {

    private final static Logger LOGGER = LogManager.getLogger(ConnectionPool.class);
    private final static AtomicReference<ConnectionPool> INSTANCE = new AtomicReference<>();
    private final static ReentrantLock INSTANCE_LOCK = new ReentrantLock();

    private final Queue<ProxyConnection> availableConnections;
    private final Queue<ProxyConnection> connectionsInUse;
    private final ReentrantLock connectionsLock;

    private int poolSize;
    private Semaphore semaphore;

    ConnectionPool(int poolSize, List<ProxyConnection> connections) {
        this.poolSize = poolSize;
        this.semaphore = new Semaphore(poolSize, true);
        this.availableConnections = new ArrayDeque<>();
        this.connectionsInUse = new ArrayDeque<>();
        this.connectionsLock = new ReentrantLock();
        for(ProxyConnection connection : connections) {
            connection.setUpConnectionPool(this);
        }
        this.availableConnections.addAll(connections);
    }

    public static ConnectionPool getInstance() {
        if(INSTANCE.get() == null) {
            try{
                INSTANCE_LOCK.lock();
                if(INSTANCE.get() == null) {
                    ConnectionPoolFactory factory = new ConnectionPoolFactory();
                    ConnectionPool pool = factory.createConnectionPool();
                    INSTANCE.getAndSet(pool);
                }
            } finally {
                INSTANCE_LOCK.unlock();
            }
        }
        return INSTANCE.get();
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
            throw new ConnectionPoolException(e);
        } finally {
            connectionsLock.unlock();
        }
    }

}
