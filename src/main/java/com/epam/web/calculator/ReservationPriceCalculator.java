package com.epam.web.calculator;

import java.math.BigDecimal;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class ReservationPriceCalculator {

    private final static double BUDGET_CLASS_PRICE = 100.0;
    private final static double BEACH_CLASS_PRICE = 150.0;
    private final static double SKI_CLASS_PRICE = 200.0;
    private final static double THEMED_CLASS_PRICE = 250.0;
    private final static double URBAN_CLASS_PRICE = 300.0;
    private final static String BUDGET_CLASS = "BUDGET";
    private final static String BEACH_CLASS = "BEACH";
    private final static String SKI_CLASS = "SKI";
    private final static String THEMED_CLASS = "THEMED";
    private final static String URBAN_CLASS = "URBAN";

    public BigDecimal calculatePrice(Date arrivalDate, Date departureDate, String roomClass) {
        long days = arrivalDate.getTime() - departureDate.getTime();
        long duration = TimeUnit.DAYS.convert(days, TimeUnit.MILLISECONDS);
        switch (roomClass) {
            case BUDGET_CLASS:
                return BigDecimal.valueOf(Math.abs(duration * BUDGET_CLASS_PRICE));

            case BEACH_CLASS:
                return BigDecimal.valueOf(Math.abs(duration * BEACH_CLASS_PRICE));

            case SKI_CLASS:
                return BigDecimal.valueOf(Math.abs(duration * SKI_CLASS_PRICE));

            case THEMED_CLASS:
                return BigDecimal.valueOf(Math.abs(duration * THEMED_CLASS_PRICE));

            case URBAN_CLASS:
                return BigDecimal.valueOf(Math.abs(duration * URBAN_CLASS_PRICE));
        }
        throw new IllegalArgumentException("Unknown room class " + roomClass);
    }

}
