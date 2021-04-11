package com.epam.web.command;

import com.epam.web.entitiy.Order;
import com.epam.web.entitiy.User;
import com.epam.web.service.OrderService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class MyOrdersCommand implements Command {

    private final OrderService service;

    public MyOrdersCommand(OrderService service) {
        this.service = service;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        User user = (User) request.getSession().getAttribute("user");
        List<Order> orders = service.getCurrentUserOrders(user);
        request.setAttribute("orders", orders);
        return CommandResult.forward("/WEB-INF/view/myorders.jsp");
    }

}
