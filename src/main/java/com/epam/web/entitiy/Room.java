package com.epam.web.entitiy;

public class Room implements Identifiable {

    public static final String TABLE = "room";
    public static final String ID = "id";
    public static final String HOTEL_ID = "hotel_id";
    public static final String CLASS = "class";
    public static final String PLACES = "places";
    public static final String BOOKED_UNTIL = "booked_until";

    private final long id;
    private final long hotelId;
    private final String roomClass;
    private final int placeCount;
    private final String bookedUntil;

    public Room(long id, long hotelId, String roomClass, int placeCount, String bookedUntil) {
        this.id = id;
        this.hotelId = hotelId;
        this.roomClass = roomClass;
        this.placeCount = placeCount;
        this.bookedUntil = bookedUntil;
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

    public String getBookedUntil() {
        return this.bookedUntil;
    }

    @Override
    public long getId() {
        return this.id;
    }

}
