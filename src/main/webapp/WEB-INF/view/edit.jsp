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
        <fmt:message key="label.editTitle"/>
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
                <form id="addroom" class="section__form" action="controller?command=editOrder&orderId=${orderId}"
                      method="post" onsubmit="return validateBookingForm()">
                    <fmt:message key="label.hotelName" var="name"/>
                    <%--                    <input id="nameOfHotel" class="form__input" type="text" placeholder="${name}" name="hotelName" autofocus required>--%>
                    <select class="classes" id="classes" name="hotelName" form="addroom" required>
                        <option value="">
                            <fmt:message key="label.chooseHotel"/>
                        </option>
                        <c:forEach items="${hotels}" var="hotel">
                            <option value="${hotel.getName()}">${hotel.getName()}</option>
                        </c:forEach>
                    </select>
                    <div id="errorHotel" class="error-div">
                        <fmt:message key="label.enterCorrectNameOfHotel"/>
                    </div>
                    <select class="classes" id="classes" name="class" form="addroom" required>
                        <option value=""><fmt:message key="label.chooseClass"/></option>
                        <option value="BUDGET">Budget</option>
                        <option value="BEACH">Beach</option>
                        <option value="SKI">Ski</option>
                        <option value="THEMED">Themed</option>
                        <option value="URBAN">Urban</option>
                    </select>
                    <fmt:message key="label.places" var="places"/>
                    <input id="places" class="form__input" type="text" placeholder="${places}" name="places" required>
                    <div id="errorPlaces" class="error-div">
                        <fmt:message key="label.enterCorrectPlaceCount"/>
                    </div>
                    <fmt:message key="label.arrival" var="arrival"/>
                    <input id="arr" class="form__input" type="date" placeholder="${arrival}" name="arrival" required>
                    <fmt:message key="label.departure" var="departure"/>
                    <input id="dep" class="form__input" type="date" placeholder="${departure}" name="departure"
                           required>
                    <div id="errorDates" class="error-div">
                        <fmt:message key="label.enterCorrectDates"/>
                    </div>
                    <fmt:message key="label.edit" var="edit"/>
                    <button class="form__button button-hover" type="submit">${edit}</button>
                </form>
            </div>
        </section>
        <c:if test="${invalidPlaceCount != null}">
            <p style="color: darkred; margin-top: 10px">
                <fmt:message key="label.invalidPlaceCount"/>
            </p>
        </c:if>
        <c:if test="${error != null}">
            <p style="color: darkred; margin-top: 10px">
                <fmt:message key="label.invalidInfo"/>
            </p>
        </c:if>
    </div>
</main>
<script>
    makeDark();
</script>
</body>
</html>