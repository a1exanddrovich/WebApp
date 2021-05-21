package com.epam.web.command;

import com.epam.web.dao.DaoHelperFactory;
import com.epam.web.service.*;
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
    private final static String CHANGE_LANGUAGE = "changeLocalization";
    private final static String INDEX = "index";
//    private final static String PHOTO = "adminAddHotelImage";

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
                return new LoginCommand(userService);

            case LOGOUT:
                return new LogoutCommand();

            case MAIN:
                return new MainPageCommand(hotelService);

            case MY_ORDERS:
                return new MyOrdersCommand(orderService);

            case MY_RESERVATIONS:
                return new MyReservationsCommand(reservationService, orderService);

            case BOOKING:
                return new ShowPageCommand("/WEB-INF/view/booking.jsp");

            case SHOW_EDIT:
                return new ShowPageCommand("/WEB-INF/view/edit.jsp");

            case INDEX:
                return new ShowPageCommand("/index.jsp");

            case ALL_ORDERS:
                return new AdminAllOrdersPageCommand(orderService);

            case FIND_ROOM:
                return new FindRoomCommand(roomService, hotelService);

            case DECLINE_ORDER:
                return new DeclineOrderCommand(orderService);

            case MAKE_RESERVATION:
                return new MakeReservationCommand(reservationService, roomService, orderService);

            case SHOW_ADD_ROOM:
                return new ShowPageCommand("/WEB-INF/view/admin/adminaddroom.jsp");

            case SHOW_ADD_HOTEL:
                return new ShowPageCommand("/WEB-INF/view/admin/adminaddhotel.jsp");

            case ADD_HOTEL:
                ServletFileUpload servletFileUpload = new ServletFileUpload(new DiskFileItemFactory());
                return new AdminAddHotelCommand(hotelService, servletFileUpload);

            case ADD_ROOM:
                return new AdminAddRoomCommand(roomService, hotelService);

            case SHOW_BALANCE:
                return new ShowBalanceCommand(userService);

            case TOP_UP_BALANCE:
                return new TopUpBalanceCommand(userService);

            case MAKE_PAYMENT:
                return new PaymentCommand(userService, hotelService);

            case REFUSE_RESERVATION:
                return new RefuseReservationCommand(reservationService);

            case EDIT_ORDER:
                return new EditOrderCommand(orderService);

            case DELETE_ORDER:
                return new DeleteOrderCommand(orderService);

            case MAKE_ORDER:
                return new MakeOrderCommand(orderService);

//            case PHOTO:
//                ServletFileUpload servletFileUpload = new ServletFileUpload(new DiskFileItemFactory());
//                return

//            case "enIndex":
//                return new ChangeLanguageCommand("en", "/index.jsp");
//
//            case "ruIndex":
//                return new ChangeLanguageCommand("ru", "/index.jsp");
//
//            case "esIndex":
//                return new ChangeLanguageCommand("es", "/index.jsp");

//            case CHANGE_LANGUAGE:
//                return new ChangeLanguageCommandFull();

            default:
                throw new IllegalArgumentException("Unknown type " + type);
        }
    }

}