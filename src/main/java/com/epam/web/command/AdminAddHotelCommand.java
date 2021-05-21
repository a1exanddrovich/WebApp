package com.epam.web.command;

import com.epam.web.entitiy.Hotel;
import com.epam.web.service.HotelService;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class AdminAddHotelCommand implements Command {

    private final static String HOTEL_NAME = "hotelName";
    private final static String DESCRIPTION = "description";
    private final static String PHOTO_ID = "photoId";
    private static final String DELIMITER = "\\";
    private static final String INIT_PARAMETER = "file-upload";

    private final HotelService service;
    private final ServletFileUpload servletFileUpload;

    public AdminAddHotelCommand(HotelService service, ServletFileUpload servletFileUpload) {
        this.service = service;
        this.servletFileUpload = servletFileUpload;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws FileUploadException {
// //       Map<String, String[]> multipartRequestParams = request.getParameterMap();
////        String[] hotelName = multipartRequestParams.get(HOTEL_NAME);
////        String hotelName = request.getParameter(HOTEL_NAME);
////        String description = request.getParameter(DESCRIPTION);
//        String hotelName = null;
//        String description = null;
//        FileItemIterator iterator = null;
//        try {
//            iterator = servletFileUpload.getItemIterator(request);
//            while(iterator.hasNext()) {
//                FileItemStream item = iterator.next();
//                InputStream stream = item.openStream();
//                if (item.isFormField()) {
//                        String inputName = item.getFieldName();
//                        if(inputName.equalsIgnoreCase("hotelName")){
////                            hotelName = (String)item.getString();
////                            System.out.println("UserName is:"+username);
//                        }
//
//                }
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//
//
//
//
String hotelName = null;
String description = null;
String filePath = null;
String fileName = null;
        FileItem image = null;
        List<FileItem> multiparts = servletFileUpload.parseRequest(request);
        String inputName = null;
        for(FileItem item : multiparts){
            if(!item.isFormField()) {
                image = item;
                fileName = item.getName();
                ServletContext servletContext = request.getSession().getServletContext();
                filePath = servletContext.getInitParameter(INIT_PARAMETER) + DELIMITER + fileName;
            }
            if(item.isFormField()){
                inputName = item.getFieldName();
                if(inputName.equalsIgnoreCase("hotelName")){
                    hotelName = item.getString();
                }
                if(inputName.equalsIgnoreCase("description")){
                    description = item.getString();
                }
            }
        }

        List<FileItem> multiParts = servletFileUpload.parseRequest(request);
        //FileItem item = multiParts.get(0);
//        String fileName = item.getName();
//        ServletContext servletContext = request.getSession().getServletContext();
//        String filePath = servletContext.getInitParameter(INIT_PARAMETER) + DELIMITER + fileName;
        //long photoId = Long.parseLong(request.getParameter(PHOTO_ID));
        //Hotel hotel = new Hotel(0, hotelName, description, photoId, new BigDecimal(0));
        Hotel hotel = new Hotel(0, hotelName, description, fileName, new BigDecimal(0));
        service.addHotel(hotel, filePath, image);
        return CommandResult.redirect("controller?command=adminShowAddHotelPage");
    }

}
