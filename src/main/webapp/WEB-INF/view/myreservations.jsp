<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>My resvs</title>
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
                                    <h3 class="app__info-text-title">Order id</h3>
                                    <p class="app__info-text-data">${reservation.getOrderId()}</p>
                                </div>
                                <div class="app__info-text">
                                    <h3 class="app__info-text-title">Hotel id</h3>
                                    <p class="app__info-text-data">${reservation.getHotelId()}</p>
                                </div>
                                <div class="app__info-text">
                                    <h3 class="app__info-text-title">User id</h3>
                                    <p class="app__info-text-data">${reservation.getUserId()}</p>
                                </div>
                                <div class="app__info-text">
                                    <h3 class="app__info-text-title">Room id</h3>
                                    <p class="app__info-text-data">${reservation.getRoomId()}</p>
                                </div>
                                <div class="app__info-text">
                                    <h3 class="app__info-text-title">Price</h3>
                                    <p class="app__info-text-data">$${reservation.getPrice()}</p>
                                </div>
                            </div>
                            <div class="app__actions">
                                <button class="app__button button-hover" type="submit">Pay</button>
                                <button class="app__button button-hover" type="submit">Refuse</button>
                            </div>
                        </div>
                    </c:forEach>
                </section>
            </c:when>
            <c:otherwise>
                <h2>Nothing to show</h2>
            </c:otherwise>
        </c:choose>
    </div>
</main>
<c:if test="${reservations.size() != 0}">
    <jsp:include page="fragments/footer.jsp"/>
</c:if>
<script>
    const lightTheme = window.sessionStorage.getItem("lightTheme");
    const darkTheme = window.sessionStorage.getItem("darkTheme");
    if (lightTheme === "false" && darkTheme === "true") {
        getDarkTheme();
    }
    if (lightTheme === "true" && darkTheme === "false") {
        getLightTheme();
    }
</script>
</body>
</html>
