package com.epam.web.entitiy;

import java.math.BigDecimal;

public class Hotel implements Identifiable {

    private final long id;
    private final String name;
    private final String description;
    private final String imagePath;
    private final BigDecimal balance;

    public Hotel(long id, String name, String description, String imagePath, BigDecimal balance) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.imagePath = imagePath;
        this.balance = balance;
    }

    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }

    public String getImagePath() {
        return this.imagePath;
    }

    public BigDecimal getBalance() {
        return this.balance;
    }

    @Override
    public long getId() {
        return this.id;
    }

}
