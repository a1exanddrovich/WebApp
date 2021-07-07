package com.epam.web.extractor;

import com.epam.web.entitiy.Identifiable;
import com.epam.web.exception.ServiceException;
import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;

/**
 * This interface is implemented by classes which create object on the base of the data extracted from {@link HttpServletRequest}
 *
 * @author Alexey Bliznichenko
 * @param <T> has to implement {@link Identifiable} interface
 */
public interface Extractor<T extends Identifiable> {

    /**
     * Creates an object on the base of extracted from {@link HttpServletRequest} data
     *
     * @param request {@link HttpServletRequest}
     * @return Identifiable {@link Identifiable}
     * @throws ParseException {@link ParseException} Exceptions caused by parse methods
     * @throws ServiceException {@link ServiceException} Exceptions on a service layer
     */
    T extract(HttpServletRequest request) throws ParseException, ServiceException;

}
