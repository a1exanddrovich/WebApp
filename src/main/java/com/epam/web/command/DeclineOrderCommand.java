package com.epam.web.command;

import com.epam.web.exception.ServiceException;
import com.epam.web.service.OrderService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeclineOrderCommand implements Command {

    private final static String ORDER_ID = "orderId";
    private final static String ALL_ORDERS_PAGE = "controller?command=adminAllOrders&currentPage=1";

    private final OrderService service;

    public DeclineOrderCommand(OrderService service) {
        this.service = service;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        long orderId = Long.parseLong(request.getParameter(ORDER_ID));
        service.declineOrderById(orderId);
        return CommandResult.redirect(ALL_ORDERS_PAGE);
    }

}
