package com.epam.web.validator;

import com.epam.web.entitiy.Hotel;

public class HotelValidator implements Validator<Hotel> {

    private final static String HOTEL_NAME_PATTERN = "[a-zA-Z_ ]{5,50}";
    private final static String DESCRIPTION_PATTERN = "[a-zA-Z1-9_ ]{1,256}";
    private final static String FILE_NAME_PATTERN = "[a-zA-Z1-9]{1,256}.[a-zA-Z1-9]{1,10}";

    @Override
    public boolean validate(Hotel entity) {
        String hotelName = entity.getName();
        String description = entity.getDescription();
        String fileName = entity.getImagePath();

        return hotelName.matches(HOTEL_NAME_PATTERN) &&
               description.matches(DESCRIPTION_PATTERN) &&
               fileName.matches(FILE_NAME_PATTERN);
    }

}
