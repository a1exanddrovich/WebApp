package com.epam.web.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ShowPageCommand implements Command {

    private final static String FALSE = "false";
    private final static String HOTEL_ADDED_PARAMETER = "showMes";
    private final static String HOTEL_ADDED_ATTRIBUTE = "hotelAddedSuccessfully";

    private final String page;

    public ShowPageCommand(String page) {
        this.page = page;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();

        if (FALSE.equals(request.getParameter(HOTEL_ADDED_PARAMETER))) {
            session.removeAttribute(HOTEL_ADDED_ATTRIBUTE);
        }

        return CommandResult.forward(page);
    }

}
