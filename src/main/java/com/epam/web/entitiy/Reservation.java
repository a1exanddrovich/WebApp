package com.epam.web.entitiy;

public class Reservation implements Identifiable {

    private final long id;
    private final long orderId;
    private final long hotelId;
    private final long roomId;
    private final long userId;
    private final long price;
    private final boolean isPaid;

    public Reservation(long id, long orderId, long hotelId, long roomId, long userId, long price, boolean isPaid) {
        this.id = id;
        this.orderId = orderId;
        this.hotelId = hotelId;
        this.roomId = roomId;
        this.userId = userId;
        this.price = price;
        this.isPaid = isPaid;
    }

    public long getOrderId() {
        return this.orderId;
    }

    public long getHotelId() {
        return this.hotelId;
    }

    public long getRoomId() {
        return this.roomId;
    }

    public long getUserId() {
        return this.userId;
    }

    public long getPrice() {
        return this.price;
    }

    public boolean isPaid() {
        return this.isPaid;
    }

    @Override
    public long getId() {
        return this.id;
    }

}
