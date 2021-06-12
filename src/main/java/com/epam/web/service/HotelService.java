package com.epam.web.service;

import com.epam.web.dao.DaoHelper;
import com.epam.web.dao.DaoHelperFactory;
import com.epam.web.dao.HotelDao;
import com.epam.web.entitiy.Hotel;
import com.epam.web.exception.DaoException;
import com.epam.web.exception.ServiceException;
import org.apache.commons.fileupload.FileItem;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class HotelService {

    private final static String JPG_FORMAT = "jpg";

    private final DaoHelperFactory factory;

    public HotelService(DaoHelperFactory factory) {
        this.factory = factory;
    }

    public List<Hotel> getAllHotels(int currentPage, int recordsPerPage) throws ServiceException {
        List<Hotel> hotels = new ArrayList<>();
        try(DaoHelper helper = factory.createDaoHelper()) {
            HotelDao dao = helper.createHotelDao();
            List<Hotel> retrievedHotels = dao.getAllHotels(currentPage, recordsPerPage);
            hotels.addAll(retrievedHotels);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
        return hotels;
    }

    public List<Hotel> getAllHotels() throws ServiceException {
        List<Hotel> hotels = new ArrayList<>();
        try(DaoHelper helper = factory.createDaoHelper()) {
            HotelDao dao = helper.createHotelDao();
            List<Hotel> retrievedHotels = dao.findAll();
            hotels.addAll(retrievedHotels);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
        return hotels;
    }

    public void addHotel(Hotel hotel, String filePath, FileItem item) throws ServiceException {
        try(DaoHelper helper = factory.createDaoHelper()) {
            InputStream fileContent = item.getInputStream();
            BufferedImage image = ImageIO.read(fileContent);
            ImageIO.write(image, JPG_FORMAT, new File(filePath));
            HotelDao dao = helper.createHotelDao();
            dao.save(hotel);
        } catch (DaoException | IOException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    public long getHotelIdByName(String hotelName) throws ServiceException {
        Optional<Hotel> hotel = null;
        try(DaoHelper helper = factory.createDaoHelper()) {
            HotelDao dao = helper.createHotelDao();
            hotel = dao.getHotelIdByName(hotelName);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
        return hotel.get().getId();
    }

    public int getHotelCount() throws ServiceException {
        int recordCount = 0;
        try(DaoHelper helper = factory.createDaoHelper()) {
            HotelDao dao = helper.createHotelDao();
            recordCount = dao.countHotels();
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
        return recordCount;
    }

}
