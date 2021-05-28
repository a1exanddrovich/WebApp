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
        <fmt:message key="label.addHotel"/>
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
                <form class="section__form" action="controller?command=adminAddHotel" method="post" enctype="multipart/form-data" onsubmit="return validateHotelForm()">
                    <fmt:message key="label.hotelName" var="name"/>
                    <input id="nameOfHotel" class="form__input" type="text" placeholder="${name}" name="hotelName" autofocus required>
                    <div id="errorHotel" class="error-div">
                        <fmt:message key="label.enterCorrectNameOfHotel"/>
                    </div>
                    <fmt:message key="label.description" var="description"/>
                    <input class="form__input" type="text" placeholder="${description}" name="description" maxlength="256" required>
                    <input type="file" name="file" accept="image/*"/>
                    <button class="form__button button-hover" type="submit">
                        <fmt:message key="label.add"/>
                    </button>
                </form>
                <c:if test="${error != null}">
                    <p style="color: darkred; margin-top: 10px">
                        <fmt:message key="label.invalidInfo"/>
                    </p>
                </c:if>
            </div>
        </section>
    </div>
</main>
<script>
    checkForTheme();
</script>
</body>
</html>
