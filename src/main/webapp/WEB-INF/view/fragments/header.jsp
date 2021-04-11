<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page session="true" %>

<html lang="${sessionScope.lang}">
<head>
    <title>Header</title>
</head>
<body class="body">
    <header class="header">
        <div class="container">
            <div class="header__container">
                <h1 class="header__title">Bookit</h1>
                <nav class="menu">
                    <ul class="menu__list">
                        <li class="menu__list-item">
                            <a href="controller?command=mainPage" class="menu__list-link"><fmt:message key="label.main"/></a>
                        </li>
                        <li class="menu__list-item">
                            <a href="controller?command=booking" class="menu__list-link"><fmt:message key="label.booking"/></a>
                        </li>
                        <li class="menu__list-item">
                            <a href="controller?command=myOrders" class="menu__list-link"><fmt:message key="label.orders"/></a>
                        </li>
                        <li class="menu__list-item">
                            <a href="controller?command=myReservations" class="menu__list-link"><fmt:message key="label.reservations"/></a>
                        </li>
                        <li class="menu__list-item">
                            <a href="controller?command=about" class="menu__list-link"><fmt:message key="label.about"/></a>
                        </li>
                        <li class="menu__list-item">
                            <a href="controller?command=showBalance" class="menu__list-link">Balance</a>
                        </li>
                    </ul>
                </nav>
                <a href="controller?command=logout" class="header__logout"><fmt:message key="label.logout"/></a>
                <div class="theme" onclick="setUpTheme()">
                    <img class="theme__image" src="images/icons/moon.png">
                </div>
            </div>
        </div>
    </header>
</body>
</html>