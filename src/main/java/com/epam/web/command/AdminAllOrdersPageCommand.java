package com.epam.web.command;

import com.epam.web.entitiy.Order;
import com.epam.web.service.OrderService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class AdminAllOrdersPageCommand implements Command {

    private final OrderService service;

    public AdminAllOrdersPageCommand(OrderService service) {
        this.service = service;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        List<Order> orders = service.getAllOrders();
        request.setAttribute("orders", orders);
        return CommandResult.forward("/WEB-INF/view/admin/adminallorders.jsp");
    }

}
