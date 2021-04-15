package com.epam.web.command;

import com.epam.web.dao.DaoHelperFactory;
import com.epam.web.service.*;

public class CommandFactory {

    //TO-DO
    //ADMIN
    //1.show orders with processing status only
    //2.validation of input on add hotel page
    //3.validation of input on add room page
    //4.if hotel with given name doesnt exist then show "no such hotel" inscription instead of 500 error
    //5.search only rooms with null booked until dates
    //USER
    //1.validation of input on booking page
    //2.if order has accepted status then disable edit and delete buttons
    //3.validation of input on edit page
    //4.if reservation has is_paid true status then disable refuse and pay buttons
    //5.check for enough user's money before starting payment process
    //6.validation of input on top up balance page
    //7.think og showing proper data on reservation info cards
    //OTHER
    //1.come up with a Calculator class which is able to calculate price of a reservation
    //2.finish up internationalization
    //3.money has to have decimal type instead of double
    //FURTHER REFACTORING OF THE CODE

    public Command create(String type) {
        switch (type) {
            case "login" :
                return new LoginCommand(new UserService(new DaoHelperFactory()));

            case "logout" :
                return new LogoutCommand();

            case "mainPage" :
                return new MainPageCommand(new HotelService(new DaoHelperFactory()));

            case "myOrders" :
                return new MyOrdersCommand(new OrderService(new DaoHelperFactory()));

            case "myReservations" :
                return new MyReservationsCommand(new ReservationService(new DaoHelperFactory()));

            case "booking" :
                return new ShowPageCommand("/WEB-INF/view/booking.jsp");

            case "about" :
                return new ShowPageCommand("/WEB-INF/view/about.jsp");

            case "editOrderPage" :
                return new ShowPageCommand("/WEB-INF/view/edit.jsp");

            case "adminAllOrders" :
                return new AdminAllOrdersPageCommand(new OrderService(new DaoHelperFactory()));

            case "findProperRoom" :
                return new FindRoomCommand(new RoomService(new DaoHelperFactory()),
                                           new HotelService(new DaoHelperFactory()));

            case "declineOrder" :
                return new DeclineOrderCommand(new OrderService(new DaoHelperFactory()));

            case "makeReservation" :
                return new MakeReservationCommand(new ReservationService(new DaoHelperFactory()),
                                                  new RoomService(new DaoHelperFactory()),
                                                  new OrderService(new DaoHelperFactory()));

            case "adminShowAddRoomPage" :
                return new ShowPageCommand("/WEB-INF/view/admin/adminaddroom.jsp");

            case "adminShowAddHotelPage" :
                return new ShowPageCommand("/WEB-INF/view/admin/adminaddhotel.jsp");

            case "adminAddHotel" :
                return new AdminAddHotelCommand(new HotelService(new DaoHelperFactory()));

            case "adminAddRoom" :
                return new AdminAddRoomCommand(new RoomService(new DaoHelperFactory()));

            case "showBalance" :
                return new ShowBalanceCommand(new UserService(new DaoHelperFactory()));

            case "topUpBalance" :
                return new TopUpBalanceCommand(new UserService(new DaoHelperFactory()));

            case "makePayment" :
                return new PaymentCommand(new UserService(new DaoHelperFactory()),
                                          new HotelService(new DaoHelperFactory()));

            case "refuseReservation" :
                return new RefuseReservationCommand(new ReservationService(new DaoHelperFactory()));

            case "editOrder" :
                return new EditOrderCommand(new OrderService(new DaoHelperFactory()));

            case "deleteOrder" :
                return new DeleteOrderCommand(new OrderService(new DaoHelperFactory()));

            case "enIndex":
                return new ChangeLanguageCommand("en", "/index.jsp");

            case "ruIndex":
                return new ChangeLanguageCommand("ru", "/index.jsp");

            case "esIndex":
                return new ChangeLanguageCommand("es", "/index.jsp");

            case "makeOrder":
                return new MakeOrderCommand(new OrderService(new DaoHelperFactory()));

            default:
                throw  new IllegalArgumentException("Unknown type " + type);
        }
    }

}