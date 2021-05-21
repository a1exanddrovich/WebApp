package com.epam.web.service;

import com.epam.web.dao.DaoHelper;
import com.epam.web.dao.DaoHelperFactory;
import com.epam.web.dao.HotelDao;
import com.epam.web.entitiy.Hotel;
import com.epam.web.exception.DaoException;
import org.apache.commons.fileupload.FileItem;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class HotelService {

    private final DaoHelperFactory factory;

    public HotelService(DaoHelperFactory factory) {
        this.factory = factory;
    }

    public List<Hotel> getAllHotels(int currentPage, int recordsPerPage) {
        List<Hotel> hotels = new ArrayList<>();
        try(DaoHelper helper = factory.createDaoHelper()) {
            HotelDao dao = helper.createHotelDao();
            List<Hotel> retrievedHotels = dao.getAllHotels(currentPage, recordsPerPage);
            hotels.addAll(retrievedHotels);
        } catch (SQLException | DaoException e) {
            throw new SecurityException(e);
        }
        return hotels;
    }

    public void addHotel(Hotel hotel, String filePath, FileItem item) {
        try {
            InputStream fileContent = item.getInputStream();
            BufferedImage image = ImageIO.read(fileContent);
            ImageIO.write(image, "jpg", new File(filePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        try(DaoHelper helper = factory.createDaoHelper()) {
            HotelDao dao = helper.createHotelDao();
            dao.save(hotel);
        } catch (DaoException e) {
            throw new SecurityException(e);
        }
    }

    public long getHotelIdByName(String hotelName) {
        Optional<Hotel> hotel = null;
        try(DaoHelper helper = factory.createDaoHelper()) {
            HotelDao dao = helper.createHotelDao();
            hotel = dao.getHotelIdByName(hotelName);
        } catch (SQLException | DaoException e) {
            throw new SecurityException(e);
        }
        return hotel.get().getId();
    }

    public int getHotelCount() {
        int recordCount = 0;
        try(DaoHelper helper = factory.createDaoHelper()) {
            HotelDao dao = helper.createHotelDao();
            recordCount = dao.countHotels();
        } catch (SQLException | DaoException e) {
            throw new SecurityException(e);
        }
        return recordCount;
    }

}
