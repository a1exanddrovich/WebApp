package com.epam.web.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ShowPageCommand implements Command {

    private final static String ORDER_ID= "orderId";
    private final static String HOTEL_NAME= "hotelName";

    private final String page;

    public ShowPageCommand(String page) {
        this.page = page;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        if(request.getParameter(ORDER_ID) != null) {
            request.setAttribute(ORDER_ID, request.getParameter(ORDER_ID));
        }
        if(request.getParameter(HOTEL_NAME) != null) {
            request.setAttribute(HOTEL_NAME, request.getParameter(HOTEL_NAME));
        }
        return CommandResult.forward(page);
    }

}
