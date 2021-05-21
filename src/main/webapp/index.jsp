<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" isELIgnored="false" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page session="true" %>

<%--<c:set var="lang" value="${not empty param.lang ? param.lang : not empty sessionScope.lang ? sessionScope.lang : pageContext.request.locale}" scope="session"/>--%>
<%--<fmt:setLocale value="${lang}" scope="session"/>--%>
<%--<fmt:setBundle basename="messages" scope="session"/>--%>

<fmt:setLocale value="${sessionScope.lang != null ? sessionScope.lang : 'en'}" scope="session"/>
<fmt:setBundle basename="messages" scope="session"/>

<html lang="${lang}">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>
        <fmt:message key="label.indexTitle"/>
    </title>
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=Roboto+Condensed:ital,wght@0,300;0,400;0,700;1,300&display=swap"
          rel="stylesheet">
    <link rel="stylesheet" href="static/reset.css">
    <link rel="stylesheet" href="static/style.css">
    <script src="script/main.js"></script>
    <script>
        window.sessionStorage.setItem("lightTheme", "true");
        window.sessionStorage.setItem("darkTheme", "false");
    </script>
</head>
<body class="body">
<header class="header">
    <div class="container">
        <div class="header__container">
            <h1 class="header__title">Bookit</h1>
            <div style="display: flex; align-content: center">
                <div style="margin-right: 20px" class="theme" onclick="setUpTheme()">
                    <img class="theme__image moon" src="images/icons/moon.png">
                </div>
                <div class="dropdown lang">
                    <div class="theme" onclick="dropdown()">
                        <img class="dropbutton theme__image" src="images/icons/lang_light.png">
                    </div>
                    <div class="dropdown-content" id="drop">
                        <a href="controller?command=index&lang=en" class="menu__list-link dropdown-link">
                            En
                        </a>
                        <a href="controller?command=index&lang=ru" class="menu__list-link dropdown-link">
                            Ru
                        </a>
                        <a href="controller?command=index&lang=es" class="menu__list-link dropdown-link">
                            Esp
                        </a>
                    </div>
                </div>
            </div>
<%--            <div class="login__actions">--%>
<%--                <a href="${pageContext.request.contextPath}/controller?command=index&lang=en" class="header__language">en</a>--%>
<%--                <a href="${pageContext.request.contextPath}/controller?command=index&lang=ru" class="header__language">ru</a>--%>
<%--                <a href="${pageContext.request.contextPath}/controller?command=index&lang=es" class="header__language">esp</a>--%>
<%--            </div>--%>
<%--            <div class="login__actions">--%>
<%--                <a href="${pageContext.request.contextPath}/controller?${pageContext.request.queryString}&lang=en" class="header__language">en</a>--%>
<%--                <a href="${pageContext.request.contextPath}/controller?command=ruIndex" class="header__language">ru</a>--%>
<%--                <a href="${pageContext.request.contextPath}/controller?command=esIndex" class="header__language">es</a>--%>
<%--            </div>--%>
        </div>
    </div>
</header>
<main class="main">
    <div class="container">
        <section class="section">
            <div class="login__container">
                <form class="section__form login__form" action="${pageContext.request.contextPath}/controller?command=login"
                      method="post">
                    <fmt:message key="label.loginUsername" var="username"/>
                    <input class="form__input login form__input-login" type="text" placeholder="${username}" name="username" autofocus>
                    <fmt:message key="label.loginPassword" var="password"/>
                    <input class="form__input login form__input-login" type="password" placeholder="${password}" name="password">
                    <fmt:message key="label.login" var="loginButton"/>
                    <button class="form__button button-hover" type="submit">${loginButton}</button>
                </form>
                <fmt:message key="label.invalidLogin" var="invalidLogin"/>
                <c:if test="${errorMessage != null}">
                    <div class="invalid__login-div">
                        <h2 class="invalid__login">${invalidLogin}</h2>
                    </div>
                </c:if>
            </div>
        </section>
    </div>
</main>
</body>
</html>