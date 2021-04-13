package com.epam.web.command;

import com.epam.web.dao.DaoHelperFactory;
import com.epam.web.service.*;

public class CommandFactory {

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
