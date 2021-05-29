package com.epam.web.command;

import com.epam.web.entitiy.Order;
import com.epam.web.exception.ServiceException;
import com.epam.web.extractor.OrderExtractor;
import com.epam.web.service.OrderService;
import com.epam.web.validator.OrderValidator;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class MakeOrderCommand implements Command {

    private final static String DATE_FORMAT = "yyyy-MM-dd";

    private final OrderService service;
    private final DateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
    private final OrderValidator validator;
    private final OrderExtractor extractor;

    public MakeOrderCommand(OrderService service, OrderValidator validator, OrderExtractor extractor) {
        this.service = service;
        this.validator = validator;
        this.extractor = extractor;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        Order extractedOrder = null;
        try{
            extractedOrder = extractor.extract(request);
        } catch (NumberFormatException e) {
            request.setAttribute("invalidPlaceCount", "incorrectData");
            return CommandResult.forward("/WEB-INF/view/booking.jsp");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if(validator.validate(extractedOrder)) {
            service.makeOrder(extractedOrder);
        } else {
            request.setAttribute("error", "incorrectData");
            return CommandResult.forward("/WEB-INF/view/booking.jsp");
        }
        return CommandResult.redirect("controller?command=booking");
    }

}
