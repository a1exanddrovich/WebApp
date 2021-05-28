package com.epam.web.validator;

import com.epam.web.entitiy.Identifiable;

/**
 * This interface is implemented by classes which validate data from client on server side
 *
 * @author Alexey Bliznichenko
 * @param <T> {@link Identifiable} object that is subjected to to be validated
 */
public interface Validator<T> {

    /**
     * Validates entity on the base of the data obtained from JSP on server side
     *
     * @param entity object
     * @return boolean
     */
    boolean validate(T entity);

}
