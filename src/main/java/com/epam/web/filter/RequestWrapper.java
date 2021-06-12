package com.epam.web.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class RequestWrapper extends HttpServletRequestWrapper {

    private final static String SPACE = " ";
    private final static String EMPTINESS = "";
    private final static String EVAL = "eval\\((.*)\\)";
    private final static String JAVASCRIPT = "[\\\"\\\'][\\s]*javascript:(.*)[\\\"\\\']";

    public RequestWrapper(HttpServletRequest request) {
        super(request);
    }

    @Override
    public String[] getParameterValues(String parameter) {
        String[] values = super.getParameterValues(parameter);
        if (values == null) {
            return null;
        }
        int count = values.length;
        String[] encodedValues = new String[count];
        for (int i = 0; i < count; i++) {
            encodedValues[i] = removeXss(values[i]);
        }
        return encodedValues;
    }

    @Override
    public String getParameter(String parameter) {
        String value = super.getParameter(parameter);
        if (value == null) {
            return null;
        }
        return removeXss(value);
    }

    @Override
    public String getHeader(String name) {
        String value = super.getHeader(name);
        if (value == null) {
            return null;
        }
        return removeXss(value);
    }

    private String removeXss(String value) {
        //value = value.replaceAll(SPACE, EMPTINESS);
        value = value.replaceAll("<", "& lt;").replaceAll(">", "& gt;");
        value = value.replaceAll("\\(", "& #40;").replaceAll("\\)", "& #41;");
        value = value.replaceAll("'", "& #39;");
        value = value.replaceAll(EVAL, EMPTINESS);
        value = value.replaceAll(JAVASCRIPT, "\"\"");
        value = value.replaceAll("script", EMPTINESS);
        return value;
    }

}
