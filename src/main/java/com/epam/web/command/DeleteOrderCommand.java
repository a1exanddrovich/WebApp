package com.epam.web.command;

import com.epam.web.service.OrderService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteOrderCommand implements Command {

    private final OrderService service;

    public DeleteOrderCommand(OrderService service) {
        this.service = service;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        String orderId = request.getParameter("orderId");
        long id = Long.parseLong(orderId);
        service.deleteOrder(id);
        return CommandResult.forward("/WEB-INF/view/successfulpage.jsp");
    }

}
