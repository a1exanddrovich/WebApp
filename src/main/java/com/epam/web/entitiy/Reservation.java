package com.epam.web.entitiy;

public class Reservation implements Identifiable {

    private long id;
    private final long orderId;
    private final long hotelId;
    private final long roomId;
    private final long userId;
    private double price;
    private boolean isPaid;

    public Reservation(long id, long orderId, long hotelId, long roomId, long userId, double price, boolean isPaid) {
        this.id = id;
        this.orderId = orderId;
        this.hotelId = hotelId;
        this.roomId = roomId;
        this.userId = userId;
        this.price = price;
        this.isPaid = isPaid;
    }

    public Reservation(long orderId, long hotelId, long roomId, long userId) {
        this.orderId = orderId;
        this.hotelId = hotelId;
        this.roomId = roomId;
        this.userId = userId;
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

    public double getPrice() {
        return this.price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isPaid() {
        return this.isPaid;
    }

    @Override
    public long getId() {
        return this.id;
    }

}
