package com.epam.web.dto;

import com.epam.web.entitiy.Identifiable;
import java.math.BigDecimal;
import java.util.Date;

public class ReservationDto implements Identifiable {

    private final long id;
    private final String hotelName;
    private final String roomClass;
    private final int places;
    private final Date arrivalDate;
    private final Date departureDate;
    private final BigDecimal price;
    private final boolean isPaid;

    public ReservationDto(long id, String hotelName, String roomClass, int places, Date arrivalDate, Date departureDate, BigDecimal price, boolean isPaid) {
        this.id = id;
        this.hotelName = hotelName;
        this.roomClass = roomClass;
        this.places = places;
        this.arrivalDate = arrivalDate;
        this.departureDate = departureDate;
        this.price = price;
        this.isPaid = isPaid;
    }

    public String getHotelName() {
        return hotelName;
    }

    public String getRoomClass() {
        return roomClass;
    }

    public int getPlaces() {
        return places;
    }

    public Date getArrivalDate() {
        return arrivalDate;
    }

    public Date getDepartureDate() {
        return departureDate;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public boolean isPaid() {
        return isPaid;
    }

    @Override
    public long getId() {
        return id;
    }
}
