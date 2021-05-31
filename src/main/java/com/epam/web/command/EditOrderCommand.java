package com.epam.web.command;

import com.epam.web.entitiy.Order;
import com.epam.web.exception.ServiceException;
import com.epam.web.extractor.OrderExtractor;
import com.epam.web.service.OrderService;
import com.epam.web.validator.OrderValidator;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;

public class EditOrderCommand implements Command {

    private final static String MY_ORDERS_COMMAND = "controller?command=myOrders&currentPage=1";
    private final static String EDIT_PAGE = "/WEB-INF/view/edit.jsp";

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
        Order extractedOrder = null;
        try{
            extractedOrder = extractor.extract(request);
        } catch (NumberFormatException e) {
            request.setAttribute("invalidPlaceCount", "incorrectData");
            return CommandResult.forward(EDIT_PAGE);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if(validator.validate(extractedOrder)) {
            service.editOrder(extractedOrder);
        } else {
            request.setAttribute("error", "incorrectData");
            return CommandResult.forward(EDIT_PAGE);
        }
        return CommandResult.redirect(MY_ORDERS_COMMAND);
    }

}
