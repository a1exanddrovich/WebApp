<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>My orders</title>
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
        <section class="section">
            <c:forEach items="${orders}" var="order">
                <div class="section__card">
                    <div class="app__info">
                        <div class="app__info-text">
                            <h3 class="app__info-text-title">Hotel</h3>
                            <p class="app__info-text-data">${order.getHotelName()}</p>
                        </div>
                        <div class="app__info-text">
                            <h3 class="app__info-text-title">Class</h3>
                            <p class="app__info-text-data">${order.getRoomClass()}</p>
                        </div>
                        <div class="app__info-text">
                            <h3 class="app__info-text-title">Places</h3>
                            <p class="app__info-text-data">${order.getPlaceCount()}</p>
                        </div>
                        <div class="app__info-text">
                            <h3 class="app__info-text-title">Arr Date</h3>
                            <p class="app__info-text-data">${order.getArrivalDate()}</p>
                        </div>
                        <div class="app__info-text">
                            <h3 class="app__info-text-title">Dep Date</h3>
                            <p class="app__info-text-data">${order.getDepartureDate()}</p>
                        </div>
                        <div class="app__info-text">
                            <h3 class="app__info-text-title">Status</h3>
                            <p class="app__info-text-data">${order.getStatus()}</p>
                        </div>
                    </div>
                    <div class="app__actions">
                        <button class="app__button button-hover" type="submit">Edit</button>
                        <button class="app__button button-hover" type="submit">Delete</button>
                    </div>
                </div>
            </c:forEach>
        </section>
    </div>
</main>
<jsp:include page="fragments/footer.jsp"/>
<script>
    const lightTheme = window.sessionStorage.getItem("lightTheme");
    const darkTheme = window.sessionStorage.getItem("darkTheme");
    if(lightTheme === "false" && darkTheme === "true") {
        getDarkTheme();
    }
    if(lightTheme === "true" && darkTheme === "false") {
        getLightTheme();
    }
</script>
</body>
</html>
