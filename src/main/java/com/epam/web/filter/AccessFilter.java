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
    private final static String USER_FORWARD_PAGE = "controller?command=mainPage&currentPage=1&showMes=false";
    private final static String ADMIN_FORWARD_PAGE = "controller?command=adminAllOrders&currentPage=1&showCancel=false&showRev=false";

    private final static String LOGIN = "login";
    private final static String LOGOUT = "logout";
    private final static String INDEX = "index";
    private final static String SIGNUP = "signup";
    private final static String SIGNUP_PAGE = "signupPage";
    private final static String MAIN_PAGE = "mainPage";
    private final static String ORDERS_PAGE = "myOrders";
    private final static String RESERVATIONS_PAGE = "myReservations";
    private final static String BOOKING = "booking";
    private final static String EDIT_PAGE = "editOrderPage";
    private final static String EDIT = "editOrder";
    private final static String MAKE_ORDER = "makeOrder";
    private final static String DELETE_ORDER = "deleteOrder";
    private final static String REFUSE_RESERVATION = "refuseReservation";
    private final static String SHOW_BALANCE = "showBalance";
    private final static String TOP_UP_BALANCE = "topUpBalance";
    private final static String MAKE_PAYMENT = "makePayment";
    private final static String ALL_ORDERS = "adminAllOrders";
    private final static String ALL_USERS = "adminAllUsers";
    private final static String BLOCKING = "adminBlocking";
    private final static String DECLINE = "declineOrder";
    private final static String MAKE_RESERVATION = "makeReservation";
    private final static String FIND_ROOM = "findProperRoom";
    private final static String ADD_ROOM = "adminAddRoom";
    private final static String ADD_HOTEL = "adminAddHotel";
    private final static String ADD_ROOM_PAGE = "adminShowAddRoomPage";
    private final static String ADD_HOTEL_PAGE = "adminShowAddHotelPage";

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
        commands.put(ANONYMOUS, Arrays.asList(LOGIN, INDEX, SIGNUP_PAGE, SIGNUP));
        commands.put(UserRole.USER.name(), Arrays.asList(LOGIN, LOGOUT, MAIN_PAGE, ORDERS_PAGE, RESERVATIONS_PAGE, BOOKING,
                                                         EDIT_PAGE, SHOW_BALANCE, TOP_UP_BALANCE, MAKE_PAYMENT, REFUSE_RESERVATION,
                                                         EDIT, DELETE_ORDER, MAKE_ORDER, INDEX));
        commands.put(UserRole.ADMIN.name(), Arrays.asList(LOGIN, LOGOUT, ALL_ORDERS, FIND_ROOM, DECLINE, MAKE_RESERVATION,
                                                          ADD_ROOM_PAGE,ADD_HOTEL_PAGE, ADD_HOTEL, ADD_ROOM,
                                                          BLOCKING, ALL_USERS, INDEX));
    }

}
