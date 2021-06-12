package com.epam.web.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ShowPageCommand implements Command {

    private final String page;

    public ShowPageCommand(String page) {
        this.page = page;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        if ("false".equals(request.getParameter("showMes"))) {
            session.removeAttribute("hotelAddedSuccessfully");
        }
        return CommandResult.forward(page);
    }

}
