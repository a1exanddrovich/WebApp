package com.epam.web.validator;

import com.epam.web.entitiy.Order;
import com.epam.web.entitiy.OrderStatus;
import java.util.Date;

import com.epam.web.entitiy.RoomClass;
import org.apache.commons.lang3.EnumUtils;

public class OrderValidator implements Validator<Order> {

    @Override
    public boolean validate(Order entity) {
        String roomClass = entity.getRoomClass();
        int placeCount = entity.getPlaceCount();
        Date arrival = entity.getArrivalDate();
        Date departure = entity.getDepartureDate();
        String status = entity.getStatus();
        //new Date() gives an object initialized with current date
        Date currentDate = new Date();

        return  EnumUtils.isValidEnum(RoomClass.class, roomClass) &&
                placeCount >= 1 &&
                (arrival.compareTo(currentDate) >= 0) &&
                (departure.compareTo(currentDate) >= 0) &&
                (arrival.compareTo(departure) < 0) &&
                EnumUtils.isValidEnum(OrderStatus.class, status);
    }

}
