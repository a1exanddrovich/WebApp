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
        Users
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
            <c:when test="${users.size() != 0}">
                <section class="section">
                    <c:forEach items="${users}" var="user">
                            <div class="section__card">
                                <div class="app__info">
                                    <div class="app__info-text">
                                        <h3 class="app__info-text-title">
                                            <fmt:message key="label.userId"/>
                                        </h3>
                                        <p class="app__info-text-data">#${user.getId()}</p>
                                    </div>
                                    <div class="app__info-text">
                                        <h3 class="app__info-text-title">
                                            <fmt:message key="label.userLogin"/>
                                        </h3>
                                        <p class="app__info-text-data">${user.getLogin()}</p>
                                    </div>
                                    <div class="app__info-text">
                                        <h3 class="app__info-text-title">
                                            <fmt:message key="label.balance"/>
                                        </h3>
                                        <p class="app__info-text-data">${user.getBalance()}$</p>
                                    </div>
                                    <div class="app__info-text">
                                        <h3 class="app__info-text-title">
                                            <fmt:message key="label.userStatus"/>
                                        </h3>
                                        <c:choose>
                                            <c:when test="${user.getIsBlocked() == true}">
                                                <p class="app__info-text-data">
                                                    <fmt:message key="label.userBlockedStatus"/>
                                                </p>
                                            </c:when>
                                            <c:otherwise>
                                                <p class="app__info-text-data">
                                                    <fmt:message key="label.userUnblockedStatus"/>
                                                </p>
                                            </c:otherwise>
                                        </c:choose>
                                    </div>
                                </div>
                                <div class="app__actions">
                                    <button class="app__button button-hover" type="submit">
                                        <a href="controller?command=adminBlocking&userId=${user.getId()}&block=${not user.getIsBlocked()}">
                                            <c:choose>
                                                <c:when test="${user.getIsBlocked() == false}">
                                                    <fmt:message key="label.blockUser"/>
                                                </c:when>
                                                <c:otherwise>
                                                    <fmt:message key="label.unblockUser"/>
                                                </c:otherwise>
                                            </c:choose>
                                        </a>
                                    </button>
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
    <ctg:pagination commandName="adminAllOrders" totalPages="${pageNumber}" currentPage="${currentPage}" nextTitle="${next}" previousTitle="${previous}"/>
</div>
<script>
    checkForTheme();
</script>
</body>
</html>
