package com.epam.web.command;

import com.epam.web.entitiy.Order;
import com.epam.web.exception.ServiceException;
import com.epam.web.extractor.OrderExtractor;
import com.epam.web.service.OrderService;
import com.epam.web.validator.OrderValidator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.text.ParseException;

public class MakeOrderCommand implements Command {

    private final static String BOOKING_PAGE = "/WEB-INF/view/booking.jsp";
    private final static String BOOKING_COMMAND = "controller?command=booking";
    private final static String INVALID_PLACE_COUNT = "invalidPlaceCount";
    private final static String INVALID_DATE = "invalidDate";
    private final static String ERROR = "error";
    private final static String BOOKED_SUCCESSFULLY = "bookedSuccessfully";

    private final OrderService service;
    private final OrderValidator validator;
    private final OrderExtractor extractor;

    public MakeOrderCommand(OrderService service, OrderValidator validator, OrderExtractor extractor) {
        this.service = service;
        this.validator = validator;
        this.extractor = extractor;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        HttpSession session = request.getSession();

        if (session.getAttribute(BOOKED_SUCCESSFULLY) != null) {
            session.removeAttribute(BOOKED_SUCCESSFULLY);
        }

        Order extractedOrder = null;

        try {
            extractedOrder = extractor.extract(request);
        } catch (NumberFormatException e) {
            request.setAttribute(INVALID_PLACE_COUNT, true);
            return CommandResult.forward(BOOKING_PAGE);
        } catch (ParseException e) {
            request.setAttribute(INVALID_DATE, true);
            return CommandResult.forward(BOOKING_PAGE);
        }

        if (validator.validate(extractedOrder)) {
            service.makeOrder(extractedOrder);
        } else {
            request.setAttribute(ERROR, true);
            return CommandResult.forward(BOOKING_PAGE);
        }

        session.setAttribute(BOOKED_SUCCESSFULLY, true);
        return CommandResult.redirect(BOOKING_COMMAND);
    }

}
