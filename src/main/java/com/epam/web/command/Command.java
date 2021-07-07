package com.epam.web.command;

import com.epam.web.exception.ServiceException;
import org.apache.commons.fileupload.FileUploadException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * This interface represents GOF pattern Command and is used as a part of MVC pattern
 *
 * @author Alexey Bliznichenko
 * @see CommandResult
 * @see HttpServletRequest
 * @see HttpServletResponse
 */
public interface Command {

    /**
     * Handles requests and responses deputed from Controller {@link com.epam.web.Controller}
     *
     * @param request {@link HttpServletRequest}
     * @param response {@link HttpServletResponse}
     * @return CommandResult {@link CommandResult}
     * @throws FileUploadException {@link FileUploadException} Exceptions while handling files
     * @throws ServiceException {@link ServiceException} Exceptions on a service layer
     */
    CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws FileUploadException, ServiceException;

}