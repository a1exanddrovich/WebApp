package com.epam.web.validator;

import com.epam.web.entitiy.Order;

import java.util.Date;

public class OrderDateValidator implements Validator<Order> {

    @Override
    public boolean validate(Order entity) {
        Date arrival = entity.getArrivalDate();
        Date departure = entity.getDepartureDate();
        //new Date() gives an object initialized with current date
        Date currentDate = new Date();
        return (arrival.compareTo(currentDate) >= 0 &
                departure.compareTo(currentDate) >= 0 &
                arrival.compareTo(departure) < 0);
    }

}
