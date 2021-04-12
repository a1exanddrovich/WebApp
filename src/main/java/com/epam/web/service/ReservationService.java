package com.epam.web.service;

import com.epam.web.dao.DaoHelper;
import com.epam.web.dao.DaoHelperFactory;
import com.epam.web.dao.ReservationDao;
import com.epam.web.entitiy.Reservation;
import com.epam.web.entitiy.User;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReservationService {

    private final DaoHelperFactory factory;

    public ReservationService(DaoHelperFactory factory) {
        this.factory = factory;
    }

    public List<Reservation> getAllReservations() {
        List<Reservation> reservations = new ArrayList<>();
        try(DaoHelper helper = factory.createDaoHelper()) {
            ReservationDao dao = helper.createReservationDao();
            List<Reservation> retrievedReservations = dao.getAllReservations();
            reservations.addAll(retrievedReservations);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reservations;
    }

    public List<Reservation> getCurrentUserReservations(User user) {
        List<Reservation> reservations = new ArrayList<>();
        try(DaoHelper helper = factory.createDaoHelper()) {
            ReservationDao dao = helper.createReservationDao();
            List<Reservation> retrievedReservations = dao.getCurrentUserReservations(user);
            reservations.addAll(retrievedReservations);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reservations;
    }

}
