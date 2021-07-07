package com.epam.web.entitiy;

/**
 * This interface specifies that an object of a class implements this interface has an id field
 *
 * @author Alexey Bliznichenko
 */
public interface Identifiable {

    /**
     * Returns the id of the this object
     *
     * @return long
     */
    long getId();

}
