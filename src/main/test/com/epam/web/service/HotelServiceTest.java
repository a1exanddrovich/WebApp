package com.epam.web.service;

import com.epam.web.dao.DaoHelper;
import com.epam.web.dao.DaoHelperFactory;
import com.epam.web.dao.HotelDao;
import com.epam.web.dao.HotelDaoImpl;
import com.epam.web.entitiy.Hotel;
import com.epam.web.exception.DaoException;
import com.epam.web.exception.ServiceException;
import org.apache.commons.fileupload.FileItem;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

public class HotelServiceTest {

    private static DaoHelperFactory daoHelperFactory;
    private static DaoHelper daoHelper;
    private static HotelDao hotelDao;
    private static Hotel hotel;
    private static HotelService service;

    @BeforeClass
    public static void init() throws DaoException {
        daoHelper = Mockito.mock(DaoHelper.class);
        daoHelperFactory = Mockito.mock(DaoHelperFactory.class);
        hotelDao = Mockito.mock(HotelDaoImpl.class);
        hotel = Mockito.mock(Hotel.class);
        when(daoHelperFactory.createDaoHelper()).thenReturn(daoHelper);
        when(daoHelper.createHotelDao()).thenReturn(hotelDao);
        service = new HotelService(daoHelperFactory);
        Mockito.doNothing().when(daoHelper).startTransaction();
        Mockito.doNothing().when(daoHelper).endTransaction();
    }

    @Test
    public void testShouldGetAllHotels() throws DaoException, ServiceException {
        //given
        when(hotelDao.getAllHotels(anyInt(), anyInt())).thenReturn(new ArrayList<>());

        //when
        List<Hotel> hotels = service.getAllHotels(1, 1);

        //then
        Assert.assertNotNull(hotels);
    }

    @Test
    public void testShouldGetHotelIdByName() throws DaoException, ServiceException {
        //given
        when(hotelDao.getHotelIdByName(anyString())).thenReturn(Optional.of(new Hotel(34, "name", "desc", "image", new BigDecimal(0))));

        //when
        long actual = service.getHotelIdByName("HotelName");

        //then
        Assert.assertEquals(34, actual);
    }

    @Test
    public void testShouldCountHotels() throws DaoException, ServiceException {
        //given
        when(hotelDao.countHotels()).thenReturn(11);

        //when
        int actual = service.getHotelCount();

        //then
        Assert.assertEquals(11, actual);
    }

    @Test
    public void testShouldAddHotel() throws ServiceException, DaoException {
        //when
        service.addHotel(hotel, "file", Mockito.mock(FileItem.class));

        //then
        Mockito.verify(hotelDao, Mockito.times(1)).save(hotel);
    }

}
