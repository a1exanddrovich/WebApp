package com.epam.web.command;

import com.epam.web.exception.ServiceException;
import com.epam.web.service.OrderService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteOrderCommand implements Command {

    private final static String ORDER_ID = "orderId";
    private final static String MY_ORDERS_PAGE = "controller?command=myOrders&currentPage=1";

    private final OrderService service;

    public DeleteOrderCommand(OrderService service) {
        this.service = service;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        String orderId = request.getParameter(ORDER_ID);
        long id = Long.parseLong(orderId);
        service.deleteOrder(id);
        return CommandResult.redirect(MY_ORDERS_PAGE);
    }

}
