package com.epam.web.command;

import com.epam.web.entitiy.Order;
import com.epam.web.exception.ServiceException;
import com.epam.web.service.OrderService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class AllOrdersPageCommand implements Command {

    private final static String ORDERS = "orders";
    private final static int RECORDS_PER_PAGE = 9;
    private final static String PAGE_NUMBER = "pageNumber";
    private final static String CURRENT_PAGE = "currentPage";
    private final static String ALL_ORDERS_PAGE = "/WEB-INF/view/admin/adminallorders.jsp";

    private final OrderService service;

    public AllOrdersPageCommand(OrderService service) {
        this.service = service;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        HttpSession session = request.getSession();
        if("false".equals(request.getParameter("showCancel"))) {
            session.removeAttribute("orderCancelled");
        }
        if("false".equals(request.getParameter("showRev"))) {
            session.removeAttribute("roomFound");
        }
        int currentPage = Integer.parseInt(request.getParameter(CURRENT_PAGE));
        List<Order> orders = service.getAllOrders(currentPage, RECORDS_PER_PAGE);
        request.setAttribute(ORDERS, orders);
        int orderCount = service.getOrderCount();
        int pageNumber = orderCount / RECORDS_PER_PAGE;
        if (pageNumber % RECORDS_PER_PAGE > 0 && !(orderCount % RECORDS_PER_PAGE == 0)) {
            pageNumber++;
        }
        request.setAttribute(PAGE_NUMBER, pageNumber);
        request.setAttribute(CURRENT_PAGE, currentPage);
        return CommandResult.forward(ALL_ORDERS_PAGE);
    }

}
