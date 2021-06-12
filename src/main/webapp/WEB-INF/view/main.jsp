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
        <fmt:message key="label.feedTitle"/>
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
            <c:forEach items="${hotels}" var="hotel">
                <div class="section__card">
                    <div class="card-image"
                         style="background-image: url('images/photos/${hotel.getImagePath()}')"></div>
                    <div class="card-describer">
                        <div class="card-info">
                            <h4 class="hotel__name">${hotel.getName()}</h4>
                            <p class="hotel__info">${hotel.getDescription()}</p>
                        </div>
                        <a href="controller?command=booking&hotelName=${hotel.getName()}&showMes=false"
                           class="button-hover card-button">
                            <fmt:message key="label.apply"/>
                        </a>
                    </div>
                </div>
            </c:forEach>
        </section>
    </div>
</main>
<div class="container">
    <fmt:message key="label.next" var="next"/>
    <fmt:message key="label.prev" var="previous"/>
    <ctg:pagination commandName="mainPage" totalPages="${pageNumber}" currentPage="${currentPage}" nextTitle="${next}"
                    previousTitle="${previous}"/>
</div>
<jsp:include page="fragments/footer.jsp"/>
<c:if test="${registeredSuccessfully != null}">
    <fmt:message key="label.signUpSuccess" var="mes"/>
    <script>
        window.onload = function () {
            alert("${mes}");
        }
    </script>
</c:if>
<script>
    checkForTheme();
</script>
</body>
</html>