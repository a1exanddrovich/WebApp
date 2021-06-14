<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" isELIgnored="false" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page session="true" %>

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
</head>
<body class="body">
<header class="header">
    <div class="container">
        <div class="header__container">
            <h1 class="header__title">Bookit</h1>
            <div style="display: flex; align-content: center">
                <div style="margin-right: 20px" class="theme" onclick="changeTheme()">
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
        </div>
    </div>
</header>
<main class="main">
    <div class="container">
        <section class="section">
            <div class="login__container">
                <form id="loginForm" class="section__form login__form"
                      action="${pageContext.request.contextPath}/controller?command=login"
                      onsubmit="return validateLoginForm()"
                      method="post">
                    <fmt:message key="label.loginUsername" var="username"/>
                    <input id="login" class="form__input login form__input-login" type="text" placeholder="${username}"
                           name="username" autofocus required>
                    <div id="errorLogin" class="error-div">
                        <fmt:message key="label.enterCorrectLogin"/>
                    </div>
                    <fmt:message key="label.loginPassword" var="password"/>
                    <input id="password" class="form__input login form__input-login" type="password"
                           placeholder="${password}" name="password" required>
                    <div id="errorPassword" class="error-div">
                        <fmt:message key="label.enterCorrectPassword"/>
                    </div>
                    <fmt:message key="label.login" var="loginButton"/>
                    <div class="button-container">
                        <button class="form__button button-hover" type="submit">${loginButton}</button>
                        <button class="form__button button-hover" type="button">
                            <a href="controller?command=signupPage">
                                <fmt:message key="label.signUp"/>
                            </a>
                        </button>
                    </div>
                </form>
                <fmt:message key="label.invalidLogin" var="invalidLogin"/>
                <c:if test="${errorMessage != null}">
                    <div class="invalid__login-div">
                        <h2 class="invalid__login">${invalidLogin}</h2>
                    </div>
                </c:if>
                <fmt:message key="label.userBlocked" var="blocked"/>
                <c:if test="${userBlocked != null}">
                    <div class="invalid__login-div">
                        <h2 class="invalid__login">${blocked}</h2>
                    </div>
                </c:if>
            </div>
        </section>
    </div>
</main>
<script>
    makeDark();
</script>
</body>
</html>