package com.epam.web.entitiy;

import java.util.Date;

public class Room implements Identifiable {

    private long id;
    private final long hotelId;
    private final String roomClass;
    private final int placeCount;
    private Date bookedUntil;
    private Date bookedFrom;

    public Room(long id, long hotelId, String roomClass, int placeCount, Date bookedUntil, Date bookedFrom) {
        this.id = id;
        this.hotelId = hotelId;
        this.roomClass = roomClass;
        this.placeCount = placeCount;
        this.bookedUntil = bookedUntil;
        this.bookedFrom = bookedFrom;
    }

    public Room(long hotelId, String roomClass, int placeCount, Date bookedUntil, Date bookedFrom) {
        this.hotelId = hotelId;
        this.roomClass = roomClass;
        this.placeCount = placeCount;
        this.bookedUntil = bookedUntil;
        this.bookedFrom = bookedFrom;
    }

    public Room(long hotelId, String roomClass, int placeCount) {
        this.hotelId = hotelId;
        this.roomClass = roomClass;
        this.placeCount = placeCount;
    }

    public long getHotelId() {
        return this.hotelId;
    }

    public String getRoomClass() {
        return this.roomClass;
    }

    public int getPlaceCount() {
        return this.placeCount;
    }

    public Date getBookedUntil() {
        return this.bookedUntil;
    }

    public Date getBookedFrom() {
        return this.bookedFrom;
    }

    @Override
    public long getId() {
        return this.id;
    }

}
