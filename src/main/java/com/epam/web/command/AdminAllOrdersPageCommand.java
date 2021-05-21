package com.epam.web.command;

import com.epam.web.entitiy.Order;
import com.epam.web.service.OrderService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class AdminAllOrdersPageCommand implements Command {

    private final static String ALL_ORDERS_PAGE = "/WEB-INF/view/admin/adminallorders.jsp";
    private final static String ORDERS = "orders";
    private final static int RECORDS_PER_PAGE = 9;

    private final OrderService service;

    public AdminAllOrdersPageCommand(OrderService service) {
        this.service = service;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        int currentPage = Integer.parseInt(request.getParameter("currentPage"));
        List<Order> orders = service.getAllOrders(currentPage, RECORDS_PER_PAGE);
        request.setAttribute(ORDERS, orders);
        int orderCount = service.getOrderCount();
        int pageNumber = orderCount / RECORDS_PER_PAGE;
        if (pageNumber % RECORDS_PER_PAGE > 0 & !(orderCount % RECORDS_PER_PAGE == 0)) {
            pageNumber++;
        }
        request.setAttribute("pageNumber", pageNumber);
        request.setAttribute("currentPage", currentPage);
        return CommandResult.forward(ALL_ORDERS_PAGE);
    }

}
