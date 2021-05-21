package com.epam.web.command;

import com.epam.web.entitiy.Order;
import com.epam.web.entitiy.User;
import com.epam.web.service.OrderService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class MyOrdersCommand implements Command {

    private final static int RECORDS_PER_PAGE = 9;

    private final OrderService service;

    public MyOrdersCommand(OrderService service) {
        this.service = service;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        int currentPage = Integer.parseInt(request.getParameter("currentPage"));
        User user = (User) request.getSession().getAttribute("user");
        List<Order> orders = service.getCurrentUserOrders(user, currentPage, RECORDS_PER_PAGE);
        request.setAttribute("orders", orders);
        int orderCount = service.getCurrentUserOrdersCount(user.getId());
        int pageNumber = orderCount / RECORDS_PER_PAGE;
        if (pageNumber % RECORDS_PER_PAGE > 0 && !(orderCount % RECORDS_PER_PAGE == 0)) {
            pageNumber++;
        }
        request.setAttribute("pageNumber", pageNumber);
        request.setAttribute("currentPage", currentPage);
        return CommandResult.forward("/WEB-INF/view/myorders.jsp");
    }

}
