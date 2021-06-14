package com.epam.web.command;

import com.epam.web.exception.ServiceException;
import com.epam.web.service.UserService;
import org.apache.commons.fileupload.FileUploadException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BlockingUsersCommand implements Command {

    private final static String BLOCK = "block";
    private final static String USER_ID = "userId";
    private final static String ALL_USERS_PAGE = "controller?command=adminAllUsers&currentPage=1";

    private final UserService service;

    public BlockingUsersCommand(UserService service) {
        this.service = service;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws FileUploadException, ServiceException {
        boolean toBeBlocked = Boolean.parseBoolean(request.getParameter(BLOCK));
        long userId = Long.parseLong(request.getParameter(USER_ID));

        service.blockUser(userId, toBeBlocked);

        return CommandResult.redirect(ALL_USERS_PAGE);
    }

}