package com.epam.web.entitiy;

import java.util.Date;

public class Order implements Identifiable {

    private long id;
    private final long userId;
    private final String hotelName;
    private final String roomClass;
    private final int placeCount;
    private final Date arrivalDate;
    private final Date departureDate;
    private final OrderStatus status;

    public Order(long id, long userId, String hotelName, String roomClass, int placeCount, Date arrivalDate, Date departureDate, OrderStatus status) {
        this.id = id;
        this.userId = userId;
        this.hotelName = hotelName;
        this.roomClass = roomClass;
        this.placeCount = placeCount;
        this.arrivalDate = arrivalDate;
        this.departureDate = departureDate;
        this.status = status;
    }

    public Order(long userId, String hotelName, String roomClass, int placeCount, Date arrivalDate, Date departureDate, OrderStatus status) {
        this.userId = userId;
        this.hotelName = hotelName;
        this.roomClass = roomClass;
        this.placeCount = placeCount;
        this.arrivalDate = arrivalDate;
        this.departureDate = departureDate;
        this.status = status;
    }

    @Override
    public long getId() {
        return this.id;
    }

    public long getUserId() {
        return this.userId;
    }

    public int getPlaceCount() {
        return this.placeCount;
    }

    public String getStatus() {
        return this.status.toString();
    }

    public String getHotelName() {
        return this.hotelName;
    }

    public String getRoomClass() {
        return this.roomClass;
    }

    public Date getArrivalDate() {
        return this.arrivalDate;
    }

    public Date getDepartureDate() {
        return this.departureDate;
    }

}
