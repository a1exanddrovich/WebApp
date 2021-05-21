package com.epam.web.validator;

import java.math.BigDecimal;

public class CashValidator implements Validator<BigDecimal> {

    @Override
    public boolean validate(BigDecimal entity) {
        return entity.compareTo(BigDecimal.ZERO) >= 1;
    }

}
