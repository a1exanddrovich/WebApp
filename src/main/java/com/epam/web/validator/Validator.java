package com.epam.web.validator;

import java.util.Date;

public class Validator {

    public boolean validateDate(Date arrival, Date departure) {
        //new Date() gives an object initialized with current date
        Date currentDate = new Date();
        return (arrival.compareTo(currentDate) >= 0 &
                departure.compareTo(currentDate) >= 0 &
                arrival.compareTo(departure) < 0);
    }

    public boolean validateSum(double sum) {
        return sum >= 0;
    }

}
