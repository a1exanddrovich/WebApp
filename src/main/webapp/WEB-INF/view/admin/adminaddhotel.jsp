<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page session="true" %>

<fmt:setLocale value="${sessionScope.lang}" scope="session"/>
<fmt:setBundle basename="messages" scope="session"/>

<html lang="${sessionScope.lang}">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>
        Add a hotel
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
        <section class="section">
            <div class="section__container">
                <form class="section__form" action="controller?command=adminAddHotel" method="post">
                    <input class="form__input" type="text" placeholder="Hotel name" name="hotelName" autofocus required>
                    <input class="form__input" type="text" placeholder="Description" name="description" required>
                    <input class="form__input" type="text" placeholder="Main photo id" name="photoId" required>
                    <button class="form__button button-hover" type="submit">Add</button>
                </form>
            </div>
        </section>
    </div>
</main>
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
