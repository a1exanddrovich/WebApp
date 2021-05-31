package com.epam.web.command;

import com.epam.web.dao.DaoHelperFactory;
import com.epam.web.extractor.*;
import com.epam.web.service.*;
import com.epam.web.validator.HotelValidator;
import com.epam.web.validator.LoginValidator;
import com.epam.web.validator.OrderValidator;
import com.epam.web.validator.RoomValidator;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class CommandFactory {

    private final static String LOGIN = "login";
    private final static String LOGOUT = "logout";
    private final static String MAIN = "mainPage";
    private final static String MY_ORDERS = "myOrders";
    private final static String MY_RESERVATIONS = "myReservations";
    private final static String BOOKING = "booking";
    private final static String SHOW_EDIT = "editOrderPage";
    private final static String ALL_ORDERS = "adminAllOrders";
    private final static String FIND_ROOM = "findProperRoom";
    private final static String DECLINE_ORDER = "declineOrder";
    private final static String MAKE_RESERVATION = "makeReservation";
    private final static String SHOW_ADD_ROOM = "adminShowAddRoomPage";
    private final static String SHOW_ADD_HOTEL = "adminShowAddHotelPage";
    private final static String ADD_HOTEL = "adminAddHotel";
    private final static String ADD_ROOM = "adminAddRoom";
    private final static String SHOW_BALANCE = "showBalance";
    private final static String TOP_UP_BALANCE = "topUpBalance";
    private final static String MAKE_PAYMENT = "makePayment";
    private final static String REFUSE_RESERVATION = "refuseReservation";
    private final static String EDIT_ORDER = "editOrder";
    private final static String DELETE_ORDER = "deleteOrder";
    private final static String MAKE_ORDER = "makeOrder";
    private final static String BLOCKING = "adminBlocking";
    private final static String ALL_USERS = "adminAllUsers";
    private final static String INDEX = "index";

    private final static String BOOKING_PAGE = "/WEB-INF/view/booking.jsp";
    private final static String EDITING_PAGE = "/WEB-INF/view/edit.jsp";
    private final static String INDEX_PAGE = "/index.jsp";
    private final static String ADD_ROOM_PAGE = "/WEB-INF/view/admin/adminaddroom.jsp";
    private final static String ADD_HOTEL_PAGE = "/WEB-INF/view/admin/adminaddhotel.jsp";

    private final static String UNKNOWN_TYPE = "Unknown type ";

    private final UserService userService;
    private final HotelService hotelService;
    private final OrderService orderService;
    private final ReservationService reservationService;
    private final RoomService roomService;

    public CommandFactory() {
        DaoHelperFactory daoHelperFactory = new DaoHelperFactory();
        userService = new UserService(daoHelperFactory);
        hotelService = new HotelService(daoHelperFactory);
        orderService = new OrderService(daoHelperFactory);
        reservationService = new ReservationService(daoHelperFactory);
        roomService = new RoomService(daoHelperFactory);
    }

    public Command create(String type) {
        switch (type) {
            case LOGIN:
                return new LoginCommand(userService, new LoginValidator(), new UserExtractor());

            case LOGOUT:
                return new LogoutCommand();

            case MAIN:
                return new MainPageCommand(hotelService);

            case MY_ORDERS:
                return new MyOrdersCommand(orderService);

            case MY_RESERVATIONS:
                return new MyReservationsCommand(reservationService, orderService);

            case BOOKING:
                return new ShowPageCommand(BOOKING_PAGE);

            case SHOW_EDIT:
                return new ShowPageCommand(EDITING_PAGE);

            case INDEX:
                return new ShowPageCommand(INDEX_PAGE);

            case ALL_ORDERS:
                return new AllOrdersPageCommand(orderService);

            case FIND_ROOM:
                return new FindRoomCommand(roomService, hotelService, new RoomExtractor());

            case DECLINE_ORDER:
                return new DeclineOrderCommand(orderService);

            case MAKE_RESERVATION:
                return new MakeReservationCommand(reservationService, roomService, orderService, new ReservationExtractor());

            case SHOW_ADD_ROOM:
                return new ShowPageCommand(ADD_ROOM_PAGE);

            case SHOW_ADD_HOTEL:
                return new ShowPageCommand(ADD_HOTEL_PAGE);

            case ADD_HOTEL:
                ServletFileUpload servletFileUpload = new ServletFileUpload(new DiskFileItemFactory());
                return new AddHotelCommand(hotelService, new HotelValidator(), servletFileUpload);

            case ADD_ROOM:
                return new AddRoomCommand(roomService, new RoomValidator(), new RoomExtractor());

            case SHOW_BALANCE:
                return new ShowBalanceCommand(userService);

            case TOP_UP_BALANCE:
                return new TopUpBalanceCommand(userService);

            case MAKE_PAYMENT:
                return new PaymentCommand(userService);

            case REFUSE_RESERVATION:
                return new RefuseReservationCommand(reservationService);

            case EDIT_ORDER:
                return new EditOrderCommand(orderService , new OrderValidator(), new OrderExtractor());

            case DELETE_ORDER:
                return new DeleteOrderCommand(orderService);

            case MAKE_ORDER:
                return new MakeOrderCommand(orderService, new OrderValidator(), new OrderExtractor());

            case ALL_USERS:
                return new AllUsersPageCommand(userService);

            case BLOCKING:
                return new BlockingUsersCommand(userService);

            default:
                throw new IllegalArgumentException(UNKNOWN_TYPE + type);
        }
    }

}