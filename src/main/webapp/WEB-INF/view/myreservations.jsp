<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="ctg" uri="/WEB-INF/custom.tld" %>
<%@ page session="true" %>

<fmt:setLocale value="${sessionScope.lang != null ? sessionScope.lang : 'en'}" scope="session"/>
<fmt:setBundle basename="messages" scope="session"/>

<html lang="${sessionScope.lang}">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>
        <fmt:message key="label.myReservationsTitle"/>
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
            <c:when test="${reservations.size() != 0}">
                <section class="section">
                    <c:forEach items="${reservations}" var="reservation">
                        <div class="section__card">
                            <div class="app__info">
                                <div class="app__info-text">
                                    <h3 class="app__info-text-title">
                                        <fmt:message key="label.hotelName"/>
                                    </h3>
                                    <p class="app__info-text-data">${reservation.getHotelName()}</p>
                                </div>
                                <div class="app__info-text">
                                    <h3 class="app__info-text-title">
                                        <fmt:message key="label.class"/>
                                    </h3>
                                    <p class="app__info-text-data">${reservation.getRoomClass()}</p>
                                </div>
                                <div class="app__info-text">
                                    <h3 class="app__info-text-title">
                                        <fmt:message key="label.places"/>
                                    </h3>
                                    <p class="app__info-text-data">${reservation.getPlaces()}</p>
                                </div>
                                <div class="app__info-text">
                                    <h3 class="app__info-text-title">
                                        <fmt:message key="label.arrivalDate"/>
                                    </h3>
                                    <p class="app__info-text-data">${reservation.getArrivalDate()}</p>
                                </div>
                                <div class="app__info-text">
                                    <h3 class="app__info-text-title">
                                        <fmt:message key="label.departureDate"/>
                                    </h3>
                                    <p class="app__info-text-data">${reservation.getDepartureDate()}</p>
                                </div>
                                <div class="app__info-text">
                                    <h3 class="app__info-text-title">
                                        <fmt:message key="label.price"/>
                                    </h3>
                                    <p class="app__info-text-data">$${reservation.getPrice()}</p>
                                </div>
                                <c:choose>
                                    <c:when test="${reservation.isPaid() == true}">
                                        <div class="app__info-text">
                                            <h3 class="app__info-text-title">
                                                <fmt:message key="label.status"/>
                                            </h3>
                                            <p class="app__info-text-data">
                                                <fmt:message key="label.paid"/>
                                            </p>
                                        </div>
                                    </c:when>
                                    <c:otherwise>
                                        <div class="app__info-text">
                                            <h3 class="app__info-text-title">
                                                <fmt:message key="label.status"/>
                                            </h3>
                                            <p class="app__info-text-data">
                                                <fmt:message key="label.unpaid"/>
                                            </p>
                                        </div>
                                    </c:otherwise>
                                </c:choose>
                            </div>
                            <div class="app__actions">
                                <c:choose>
                                    <c:when test="${reservation.isPaid() == true}">
                                        <button style="cursor: unset" class="app__button" type="submit" disabled>
                                            <fmt:message key="label.pay"/>
                                        </button>
                                        <button style="cursor: unset" class="app__button" type="submit" disabled>
                                            <fmt:message key="label.refuse"/>
                                        </button>
                                    </c:when>
                                    <c:otherwise>
                                        <button class="app__button button-hover" type="submit">
                                            <a href="controller?command=makePayment&reservationId=${reservation.getId()}">
                                                <fmt:message key="label.pay"/>
                                            </a>
                                        </button>
                                        <button class="app__button button-hover" type="submit">
                                            <a href="controller?command=refuseReservation&reservationId=${reservation.getId()}">
                                                <fmt:message key="label.refuse"/>
                                            </a>
                                        </button>
                                    </c:otherwise>
                                </c:choose>
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
<div class="container">
    <fmt:message key="label.next" var="next"/>
    <fmt:message key="label.prev" var="previous"/>
    <ctg:pagination commandName="myReservations" totalPages="${pageNumber}" currentPage="${currentPage}"
                    nextTitle="${next}" previousTitle="${previous}"/>
</div>
<c:if test="${success == false}">
    <fmt:message key="label.failedMessage" var="mes"/>
    <script>
        window.onload = function () {
            alert("${mes}");
        }
    </script>
</c:if>
<c:if test="${reservations.size() != 0}">
    <jsp:include page="fragments/footer.jsp"/>
</c:if>
<script>
    checkForTheme();
</script>
</body>
</html>
