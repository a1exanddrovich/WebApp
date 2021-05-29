package com.epam.web.filter;

import com.epam.web.entitiy.User;
import com.epam.web.entitiy.UserRole;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AccessFilter implements Filter {

    private final Map<String, List<String>> commands = new HashMap<>();
    private final static String ANONYMOUS = "anonymous";
    private final static String USER = "user";
    private final static String COMMAND = "command";
    private final static String ANONYMOUS_FORWARD_PAGE = "/index.jsp";
    private final static String USER_FORWARD_PAGE = "controller?command=mainPage&currentPage=1";
    private final static String ADMIN_FORWARD_PAGE = "controller?command=adminAllOrders&currentPage=1";

    @Override
    public void init(FilterConfig filterConfig) {
        setAccess();
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(USER);
        String userRole = null;
        if (user == null) {
            userRole = ANONYMOUS;
        } else {
            userRole = user.getRole();
        }
        String command = request.getParameter(COMMAND);
        if(!commands.get(userRole).contains(command)) {
            if(ANONYMOUS.equals(userRole)) {
                request.getRequestDispatcher(ANONYMOUS_FORWARD_PAGE).forward(request, servletResponse);
            }
            if (UserRole.USER.name().equals(userRole)) {
                request.getRequestDispatcher(USER_FORWARD_PAGE).forward(request, servletResponse);
            }
            if (UserRole.ADMIN.name().equals(userRole)) {
                request.getRequestDispatcher(ADMIN_FORWARD_PAGE).forward(request, servletResponse);
            }
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() { }

    private void setAccess() {
        commands.put(ANONYMOUS, Arrays.asList("login", "index"));
        commands.put(UserRole.USER.name(), Arrays.asList("login", "logout", "mainPage", "myOrders", "myReservations", "booking",
                                                         "editOrderPage", "showBalance", "topUpBalance", "makePayment", "refuseReservation",
                                                         "editOrder", "deleteOrder", "makeOrder", "index"));
        commands.put(UserRole.ADMIN.name(), Arrays.asList("login", "logout", "adminAllOrders", "findProperRoom", "declineOrder", "makeReservation",
                                                          "adminShowAddRoomPage", "adminShowAddHotelPage", "adminAddHotel", "adminAddRoom",
                                                          "adminBlocking", "adminAllUsers", "index"));
    }

}
