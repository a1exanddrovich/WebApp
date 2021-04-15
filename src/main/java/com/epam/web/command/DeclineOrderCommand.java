package com.epam.web.command;

import com.epam.web.service.OrderService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeclineOrderCommand implements Command {

    private final OrderService service;

    public DeclineOrderCommand(OrderService service) {
        this.service = service;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        long orderId = Long.parseLong(request.getParameter("orderId"));
        service.declineOrderById(orderId);
        return CommandResult.forward("/WEB-INF/view/admin/adminsuccessfulpage.jsp");
    }

}
