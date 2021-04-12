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
        <fmt:message key="label.editTitle"/>
    </title>
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=Roboto+Condensed:ital,wght@0,300;0,400;0,700;1,300&display=swap" rel="stylesheet">
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
                <form class="section__form" action="controller?command=editOrder&orderId=${orderId}" method="post">
                    <fmt:message key="label.hotelName" var="name"/>
                    <input class="form__input" type="text" placeholder="${name}" name="hotelName" autofocus required>
                    <fmt:message key="label.class" var="roomClass"/>
                    <input class="form__input" type="text" placeholder="${roomClass}" name="class" required>
                    <fmt:message key="label.places" var="places"/>
                    <input class="form__input" type="text" placeholder="${places}" name="places" required>
                    <fmt:message key="label.arrival" var="arrival"/>
                    <input class="form__input" type="date" placeholder="${arrival}" name="arrival" required>
                    <fmt:message key="label.departure" var="departure"/>
                    <input class="form__input" type="date" placeholder="${departure}" name="departure" required>
                    <fmt:message key="label.edit" var="edit"/>
                    <button class="form__button button-hover" type="submit">${edit}</button>
                </form>
            </div>
        </section>
        <c:if test="${error != null}">
            <p style="color: darkred; margin-top: 10px">
                <fmt:message key="label.invalidDate"/>
            </p>
        </c:if>
    </div>
</main>
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