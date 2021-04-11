package com.epam.web.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ShowPageCommand implements Command {

    private final String page;

    public ShowPageCommand(String page) {
        this.page = page;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        if(request.getParameter("orderId") != null) {
            request.setAttribute("orderId", request.getParameter("orderId"));
        }
        if(request.getParameter("hotelName") != null) {
            request.setAttribute("hotelName", request.getParameter("hotelName"));
        }
        return CommandResult.forward(page);
    }

}
