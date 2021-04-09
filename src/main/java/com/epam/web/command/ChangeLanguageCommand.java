package com.epam.web.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ChangeLanguageCommand implements Command {

    private final static String LANGUAGE = "lang";

    private final String language;
    private final String page;

    public ChangeLanguageCommand(String language, String page) {
        this.language = language;
        this.page = page;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        session.setAttribute("lang", this.language);
        return CommandResult.forward(page);
    }

}
