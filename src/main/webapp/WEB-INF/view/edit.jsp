<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" isELIgnored="false" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page session="true" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Editing</title>
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
                    <input class="form__input" type="text" placeholder="Hotel name" name="hotelName" autofocus>
                    <input class="form__input" type="text" placeholder="Class" name="class">
                    <input class="form__input" type="text" placeholder="Places" name="places">
                    <input class="form__input" type="date" placeholder="Arriving date" name="arrival">
                    <input class="form__input" type="date" placeholder="Departure date" name="departure">
                    <button class="form__button button-hover" type="submit">Edit</button>
                </form>
                <div class="section__image"></div>
            </div>
        </section>
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
