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
        <fmt:message key="label.signUpTitle"/>
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
                        <a href="controller?command=signupPage&lang=en" class="menu__list-link dropdown-link">
                            En
                        </a>
                        <a href="controller?command=signupPage&lang=ru" class="menu__list-link dropdown-link">
                            Ru
                        </a>
                        <a href="controller?command=signupPage&lang=es" class="menu__list-link dropdown-link">
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
                      action="${pageContext.request.contextPath}/controller?command=signup"
                      onsubmit="return validateSignUpForm()"
                      method="post">
                    <fmt:message key="label.loginUsername" var="username"/>
                    <input id="signup" class="form__input login form__input-login" type="text" placeholder="${username}"
                           name="username" autofocus required>
                    <div id="errorLogin" class="error-div">
                        <fmt:message key="label.enterCorrectLogin"/>
                    </div>
                    <fmt:message key="label.loginPassword" var="password"/>
                    <input id="passwordFirst" class="form__input login form__input-login" type="password"
                           placeholder="${password}" name="passwordFirst" required>
                    <fmt:message key="label.submitPassword" var="submitPassword"/>
                    <input id="passwordSecond" class="form__input login form__input-login" type="password"
                           placeholder="${submitPassword}" name="passwordSecond" required>
                    <div id="errorPassword" class="error-div">
                        <fmt:message key="label.enterCorrectPassword"/>
                    </div>
                    <div id="errorPasswordTheSame" class="error-div">
                        <fmt:message key="label.multiplePasswords"/>
                    </div>
                    <button class="form__button button-hover" type="submit">
                        <fmt:message key="label.signUp"/>
                    </button>
                </form>
                <fmt:message key="label.differentPasswords" var="differentPasswords"/>

                    <div class="invalid__login-div">
                        <h2 class="invalid__login">
                        <c:if test="${differentPasswordsError != null}">
                                ${differentPasswords}
                        </c:if>
                        <c:if test="${errorMessage != null}">
                                ${invalidLogin}
                        </c:if>
                        <c:if test="${loginAlreadyTaken != null}">
                                ${loginTaken}
                        </c:if>
                        </h2>
                    </div>
            </div>
        </section>
    </div>
</main>
<script>
    makeDark();
</script>
</body>
</html>