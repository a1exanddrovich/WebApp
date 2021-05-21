<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false"%>
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
            <h1 class="header__title">[admin page]</h1>
            <nav class="menu">
                <ul class="menu__list">
                    <li class="menu__list-item">
                        <a href="controller?command=adminAllOrders&currentPage=1" class="menu__list-link">
                            <fmt:message key="label.allOrders"/>
                        </a>
                    </li>
                    <li class="menu__list-item">
                        <a href="controller?command=adminShowAddRoomPage" class="menu__list-link">
                            <fmt:message key="label.addRoom"/>
                        </a>
                    </li>
                    <li class="menu__list-item">
                        <a href="controller?command=adminShowAddHotelPage" class="menu__list-link">
                            <fmt:message key="label.addHotel"/>
                        </a>
                    </li>
                </ul>
            </nav>
            <a href="controller?command=logout" class="header__logout"><fmt:message key="label.logout"/></a>
            <div style="display: flex; align-content: center">
                <div style="margin-right: 20px" class="theme" onclick="setUpTheme()">
                    <img class="theme__image moon" src="images/icons/moon.png">
                </div>
                <div class="dropdown lang">
                    <div class="theme" onclick="dropdown()">
                        <img class="dropbutton theme__image" src="images/icons/lang_light.png">
                    </div>
                    <div class="dropdown-content" id="drop">
                        <a href="controller?${pageContext.request.queryString}&lang=en" class="menu__list-link dropdown-link">
                            En
                        </a>
                        <a href="controller?${pageContext.request.queryString}&lang=ru" class="menu__list-link dropdown-link">
                            Ru
                        </a>
                        <a href="controller?${pageContext.request.queryString}&lang=es" class="menu__list-link dropdown-link">
                            Esp
                        </a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</header>
</body>
</html>