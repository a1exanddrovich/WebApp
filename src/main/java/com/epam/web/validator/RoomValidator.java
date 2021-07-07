package com.epam.web.validator;

import com.epam.web.entitiy.Room;
import com.epam.web.entitiy.RoomClass;
import org.apache.commons.lang3.EnumUtils;

public class RoomValidator implements Validator<Room> {

    @Override
    public boolean validate(Room entity) {
        String roomClass = entity.getRoomClass().toString();
        int places = entity.getPlaceCount();

        return EnumUtils.isValidEnum(RoomClass.class, roomClass) && places >= 1;
    }

}
