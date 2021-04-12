package com.epam.web.entitiy;

public class Hotel implements Identifiable {

    private final long id;
    private final String name;
    private final String description;
    private final long imageId;

    public Hotel(long id, String name, String description, long imageId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.imageId = imageId;
    }

    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }

    public long getImageId() {
        return this.imageId;
    }

    @Override
    public long getId() {
        return this.id;
    }

}
