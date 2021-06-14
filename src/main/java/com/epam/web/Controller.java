package com.epam.web;

import com.epam.web.command.Command;
import com.epam.web.command.CommandFactory;
import com.epam.web.command.CommandResult;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Controller extends HttpServlet {

    private final static String COMMAND = "command";
    private final static String ERROR_PAGE = "/error.jsp";
    private final static Logger LOGGER = LogManager.getLogger(Controller.class);

    private final CommandFactory factory = new CommandFactory();

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        process(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        process(request, response);
    }

    private void process(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String action = request.getParameter(COMMAND);
        Command command = this.factory.create(action);
        String page = null;
        CommandResult result = null;
        boolean isRedirect = false;

        try {
            result = command.execute(request, response);
            page = result.getPage();
            isRedirect = result.isRedirect();
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            page = ERROR_PAGE;
        }

        if (!isRedirect) {
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page);
            dispatcher.forward(request, response);
        } else {
            response.sendRedirect(page);
        }

    }

}
