package com.epam.web.entitiy;

public class Hotel implements Identifiable {

    private final long id;
    private final String name;
    private final String description;

    public Hotel(long id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }

    @Override
    public long getId() {
        return this.id;
    }

}
