<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix = "ctg" uri = "/WEB-INF/custom.tld" %>
<%@ page session="true" %>

<fmt:setLocale value="${sessionScope.lang != null ? sessionScope.lang : 'en'}" scope="session"/>
<fmt:setBundle basename="messages" scope="session"/>

<html lang="${sessionScope.lang}">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>
        <fmt:message key="label.allOrdersTitle"/>
    </title>
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=Roboto+Condensed:ital,wght@0,300;0,400;0,700;1,300&display=swap"
          rel="stylesheet">
    <link rel="stylesheet" href="static/reset.css">
    <link rel="stylesheet" href="static/style.css">
    <script src="script/main.js"></script>
</head>
<body class="body">
<jsp:include page="fragments/header.jsp"/>
<main class="main">
    <div class="container">
        <c:choose>
            <c:when test="${orders.size() != 0}">
                <section class="section">
                    <c:forEach items="${orders}" var="order">
                        <c:choose>
                            <c:when test="${order.getStatus() == 'PROCESSING'}">
                                <div class="section__card">
                                    <div class="app__info">
                                        <div class="app__info-text">
                                            <h3 class="app__info-text-title">
                                                <fmt:message key="label.orderNumber"/>
                                            </h3>
                                            <p class="app__info-text-data">#${order.getId()}</p>
                                        </div>
                                        <div class="app__info-text">
                                            <h3 class="app__info-text-title">
                                                <fmt:message key="label.hotelName"/>
                                            </h3>
                                            <p class="app__info-text-data">${order.getHotelName()}</p>
                                        </div>
                                        <div class="app__info-text">
                                            <h3 class="app__info-text-title">
                                                <fmt:message key="label.class"/>
                                            </h3>
                                            <p class="app__info-text-data">${order.getRoomClass()}</p>
                                        </div>
                                        <div class="app__info-text">
                                            <h3 class="app__info-text-title">
                                                <fmt:message key="label.places"/>
                                            </h3>
                                            <p class="app__info-text-data">${order.getPlaceCount()}</p>
                                        </div>
                                        <div class="app__info-text">
                                            <h3 class="app__info-text-title">
                                                <fmt:message key="label.arrivalDate"/>
                                            </h3>
                                            <p class="app__info-text-data">${order.getArrivalDate()}</p>
                                        </div>
                                        <div class="app__info-text">
                                            <h3 class="app__info-text-title">
                                                <fmt:message key="label.departureDate"/>
                                            </h3>
                                            <p class="app__info-text-data">${order.getDepartureDate()}</p>
                                        </div>
                                    </div>
                                    <div class="app__actions">
                                        <button class="app__button button-hover" type="submit">
                                            <a href="controller?command=findProperRoom&orderId=${order.getId()}&userId=${order.getUserId()}&hotel=${order.getHotelName()}&class=${order.getRoomClass()}&places=${order.getPlaceCount()}&arrival=${order.getArrivalDate()}&departure=${order.getDepartureDate()}">
                                                <fmt:message key="label.findRoom"/>
                                            </a>
                                        </button>
                                        <button class="app__button button-hover" type="submit">
                                            <a href="controller?command=declineOrder&orderId=${order.getId()}">
                                                <fmt:message key="label.declineOrder"/>
                                            </a>
                                        </button>
                                    </div>
                                </div>
                            </c:when>
                        </c:choose>
                    </c:forEach>
                </section>
            </c:when>
            <c:otherwise>
                <h2 class="inscription">
                    <fmt:message key="label.nothingFound"/>
                </h2>
            </c:otherwise>
        </c:choose>
    </div>
</main>
<div class="container">
    <fmt:message key="label.next" var="next"/>
    <fmt:message key="label.prev" var="previous"/>
    <ctg:pagination commandName="adminAllOrders" totalPages="${pageNumber}" currentPage="${currentPage}" nextTitle="${next}" previousTitle="${previous}"/>
</div>
<script>
    checkForTheme();
</script>
</body>
</html>
