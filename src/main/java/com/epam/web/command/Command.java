package com.epam.web.command;

import org.apache.commons.fileupload.FileUploadException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Command {

    CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws FileUploadException;

}