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

public class EditOrderCommand implements Command {

    private final static String MY_ORDERS_COMMAND = "controller?command=myOrders&currentPage=1";
    private final static String EDIT_PAGE = "/controller?command=editOrderPage";
    private final static String INVALID_PLACE_COUNT = "invalidPlaceCount";
    private final static String ERROR = "error";
    private final static String EDITED_SUCCESSFULLY = "editedSuccessfully";

    private final OrderService service;
    private final OrderValidator validator;
    private final OrderExtractor extractor;

    public EditOrderCommand(OrderService service, OrderValidator validator, OrderExtractor extractor) {
        this.service = service;
        this.validator = validator;
        this.extractor = extractor;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        HttpSession session = request.getSession();
        Order extractedOrder = null;
        try{
            extractedOrder = extractor.extract(request);
        } catch (NumberFormatException | ParseException e) {
            request.setAttribute(INVALID_PLACE_COUNT, true);
            return CommandResult.forward(EDIT_PAGE);
        }
        if(validator.validate(extractedOrder)) {
            service.editOrder(extractedOrder);
        } else {
            request.setAttribute(ERROR, true);
            return CommandResult.forward(EDIT_PAGE);
        }
        session.setAttribute(EDITED_SUCCESSFULLY, true);
        return CommandResult.redirect(MY_ORDERS_COMMAND);
    }

}
