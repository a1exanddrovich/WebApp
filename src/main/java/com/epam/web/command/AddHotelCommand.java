package com.epam.web.command;

import com.epam.web.entitiy.Hotel;
import com.epam.web.exception.ServiceException;
import com.epam.web.service.HotelService;
import com.epam.web.validator.HotelValidator;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.List;

public class AddHotelCommand implements Command {

    private final static String HOTEL_NAME = "hotelName";
    private final static String DESCRIPTION = "description";
    private final static String DELIMITER = "\\";
    private final static String INIT_PARAMETER = "file-upload";
    private final static String ADD_HOTEL_COMMAND = "controller?command=adminShowAddHotelPage";
    private final static String ADD_HOTEL_PAGE = "/WEB-INF/view/admin/adminaddhotel.jsp";

    private final HotelService service;
    private final ServletFileUpload servletFileUpload;
    private final HotelValidator validator;

    public AddHotelCommand(HotelService service, HotelValidator validator, ServletFileUpload servletFileUpload) {
        this.service = service;
        this.validator = validator;
        this.servletFileUpload = servletFileUpload;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws FileUploadException, ServiceException {
        String hotelName = null;
        String description = null;
        String filePath = null;
        String fileName = null;
        FileItem image = null;
        List<FileItem> multiParts = servletFileUpload.parseRequest(request);
        for (FileItem item : multiParts) {
            if (!item.isFormField()) {
                image = item;
                fileName = item.getName();
                ServletContext servletContext = request.getSession().getServletContext();
                filePath = servletContext.getInitParameter(INIT_PARAMETER) + DELIMITER + fileName;
            }
            if (item.isFormField()) {
                String inputName = item.getFieldName();
                if (inputName.equalsIgnoreCase(HOTEL_NAME)) {
                    hotelName = item.getString();
                }
                if (inputName.equalsIgnoreCase(DESCRIPTION)) {
                    description = item.getString();
                }
            }
        }
        Hotel hotel = new Hotel(0, hotelName, description, fileName, new BigDecimal(0));
        if (!validator.validate(hotel)) {
            request.setAttribute("error", "incorrectData");
            return CommandResult.forward(ADD_HOTEL_PAGE);
        }
        service.addHotel(hotel, filePath, image);
        return CommandResult.redirect(ADD_HOTEL_COMMAND);
    }

}
