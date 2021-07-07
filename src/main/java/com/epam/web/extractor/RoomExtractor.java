package com.epam.web.extractor;

import com.epam.web.dao.DaoHelperFactory;
import com.epam.web.entitiy.Room;
import com.epam.web.entitiy.RoomClass;
import com.epam.web.exception.ServiceException;
import com.epam.web.service.HotelService;

import javax.servlet.http.HttpServletRequest;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class RoomExtractor implements Extractor<Room> {

    private final static String DATE_FORMAT = "yyyy-MM-dd";
    private final static String HOTEL_NAME = "hotelName";
    private final static String ROOM_CLASS = "class";
    private final static String PLACES = "places";
    private final static String ARRIVAL = "arrival";
    private final static String DEPARTURE = "departure";

    private final HotelService hotelService = new HotelService(new DaoHelperFactory());
    private final DateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);

    @Override
    public Room extract(HttpServletRequest request) throws ServiceException {
        String hotelName = request.getParameter(HOTEL_NAME);
        RoomClass roomClass = RoomClass.valueOf(request.getParameter(ROOM_CLASS));
        int places = Integer.parseInt(request.getParameter(PLACES));
        long hotelId = 0;
        Date arrival = null;
        Date departure = null;

        if (request.getParameter(ARRIVAL) != null && request.getParameter(DEPARTURE) != null) {
            try {
                arrival = dateFormat.parse(request.getParameter(ARRIVAL));
                departure = dateFormat.parse(request.getParameter(DEPARTURE));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        hotelId = hotelService.getHotelIdByName(hotelName);

        Room extractedRoom = new Room(0, hotelId, roomClass, places, arrival, departure);
        return extractedRoom;
    }

}
