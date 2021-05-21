package com.epam.web.entitiy;

import java.util.Date;

public class Room implements Identifiable {

    private final long id;
    private final long hotelId;
    private final RoomClass roomClass;
    private final int placeCount;
    private final Date bookedUntil;
    private final Date bookedFrom;

    public Room(long id, long hotelId, RoomClass roomClass, int placeCount, Date bookedUntil, Date bookedFrom) {
        this.id = id;
        this.hotelId = hotelId;
        this.roomClass = roomClass;
        this.placeCount = placeCount;
        this.bookedUntil = bookedUntil;
        this.bookedFrom = bookedFrom;
    }

    public long getHotelId() {
        return this.hotelId;
    }

    public RoomClass getRoomClass() {
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
