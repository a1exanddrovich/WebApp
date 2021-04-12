<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page session="true" %>

<fmt:setLocale value="${sessionScope.lang}" scope="session"/>
<fmt:setBundle basename="messages" scope="session"/>

<html lang="${sessionScope.lang}">
<head>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <title>
            <fmt:message key="label.balanceTitle"/>
        </title>
        <link rel="preconnect" href="https://fonts.gstatic.com">
        <link href="https://fonts.googleapis.com/css2?family=Roboto+Condensed:ital,wght@0,300;0,400;0,700;1,300&display=swap"
              rel="stylesheet">
        <link rel="stylesheet" href="static/reset.css">
        <link rel="stylesheet" href="static/style.css">
        <script src="script/main.js"></script>
    </head>
</head>
<body class="body">
<jsp:include page="fragments/header.jsp"/>
<main class="main">
    <div class="container">
        <form class="section__form login__form balance balance_form" action="controller?command=topUpBalance" method="post">
            <h2 class="inscription"><fmt:message key="label.yourBalance"/> ${balance} $</h2>
            <fmt:message key="label.money" var="enterSum"/>
            <input class="form__input login form__input-login" type="text" placeholder="${enterSum}, $" name="topUpBalance" autofocus>
            <button class="form__button button-hover" type="submit">
                <fmt:message key="label.topUp"/>
            </button>
        </form>
        <c:if test="${error != null}">
            <p style="color: darkred; margin-top: 10px">
                <fmt:message key="label.invalidSum"/>
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
