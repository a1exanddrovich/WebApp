package com.epam.web.command;

import com.epam.web.entitiy.User;
import com.epam.web.exception.ServiceException;
import com.epam.web.service.UserService;
import org.apache.commons.fileupload.FileUploadException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class AllUsersPageCommand implements Command {

    private final static String USERS = "users";
    private final static int RECORDS_PER_PAGE = 9;
    private final static String PAGE_NUMBER = "pageNumber";
    private final static String CURRENT_PAGE = "currentPage";
    private final static String ALL_USERS_PAGE = "/WEB-INF/view/admin/adminallusers.jsp";

    private final UserService service;

    public AllUsersPageCommand(UserService service) {
        this.service = service;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws FileUploadException, ServiceException {
        int currentPage = Integer.parseInt(request.getParameter(CURRENT_PAGE));
        List<User> users = service.getAllUsers(currentPage, RECORDS_PER_PAGE);
        request.setAttribute(USERS, users);
        int userCount = service.getUserCount();
        int pageNumber = userCount / RECORDS_PER_PAGE;
        if (pageNumber % RECORDS_PER_PAGE > 0 & !(userCount % RECORDS_PER_PAGE == 0)) {
            pageNumber++;
        }
        request.setAttribute(PAGE_NUMBER, pageNumber);
        request.setAttribute(CURRENT_PAGE, currentPage);
        return CommandResult.forward(ALL_USERS_PAGE);
    }

}
