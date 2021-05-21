package com.epam.web.validator;

public interface Validator<T> {

    boolean validate(T entity);

}
