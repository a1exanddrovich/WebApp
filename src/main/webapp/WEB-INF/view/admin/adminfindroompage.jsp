<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page session="true" %>

<fmt:setLocale value="${sessionScope.lang != null ? sessionScope.lang : 'en'}" scope="session"/>
<fmt:setBundle basename="messages" scope="session"/>

<html lang="${sessionScope.lang}">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>
        <fmt:message key="label.lookForRoom"/>
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
            <c:when test="${rooms.size() != 0}">
                <section class="section">
                    <c:forEach items="${rooms}" var="room">
                        <div class="section__card">
                            <div class="app__info">
                                <div class="app__info-text">
                                    <h3 class="app__info-text-title">
                                        <fmt:message key="label.id"/>
                                    </h3>
                                    <p class="app__info-text-data">#${room.getHotelId()}</p>
                                </div>
                                <div class="app__info-text">
                                    <h3 class="app__info-text-title">
                                        <fmt:message key="label.class"/>
                                    </h3>
                                    <p class="app__info-text-data">${room.getRoomClass()}</p>
                                </div>
                                <div class="app__info-text">
                                    <h3 class="app__info-text-title">
                                        <fmt:message key="label.places"/>
                                    </h3>
                                    <p class="app__info-text-data">${room.getPlaceCount()}</p>
                                </div>
                            </div>
                            <div class="app__actions">
                                <button class="app__button button-hover" type="submit">
                                    <a href="controller?command=makeReservation&userId=${param.userId}&hotelId=${room.getHotelId()}&roomId=${room.getId()}&orderId=${param.orderId}">
                                        <fmt:message key="label.apply"/>
                                    </a>
                                </button>
                            </div>
                        </div>
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
<script>
    makeDark();
</script>
</body>
</html>
