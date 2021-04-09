<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" isELIgnored="false" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page session="true" %>

<fmt:setLocale value="${sessionScope.lang}" scope="session"/>
<fmt:setBundle basename="messages" scope="session"/>

<html lang="${sessionScope.lang}">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Welcome</title>
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
            <div class="login__actions">
                <a href="${pageContext.request.contextPath}/controller?command=enIndex" class="header__language">en</a>
                <a href="${pageContext.request.contextPath}/controller?command=ruIndex" class="header__language">ru</a>
                <a href="${pageContext.request.contextPath}/controller?command=esIndex" class="header__language">es</a>
                <div class="theme login__theme" onclick="setUpTheme()">
                    <img class="theme__image" src="images/icons/moon.png">
                </div>
            </div>
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
<%--                <p class="about__text login__text">--%>
<%--                    Lorem ipsum dolor sit--%>
<%--                    amet, consectetur dipiscing elit,--%>
<%--                    sed do--%>
<%--                    eiusmod tempor incididunt--%>
<%--                    ut labore et dolore magna aliqua.--%>
<%--                    Lorem ipsum dolor sit--%>
<%--                    amet, consectetur dipiscing elit,--%>
<%--                    sed do--%>
<%--                    eiusmod tempor incididunt--%>
<%--                    ut labore et dolore magna aliqua.--%>
<%--                    Lorem ipsum dolor sit--%>
<%--                    Lorem ipsum dolor sit--%>
<%--                    amet, consectetur dipiscing elit,--%>
<%--                    sed do--%>
<%--                    eiusmod tempor incididunt--%>
<%--                    ut labore et dolore magna aliqua.--%>
<%--                    Lorem ipsum dolor sit--%>
<%--                    amet, consectetur dipiscing elit,--%>
<%--                    sed do--%>
<%--                    eiusmod tempor incididunt--%>
<%--                    ut labore et dolore magna aliqua.--%>
<%--                    Lorem ipsum dolor sit--%>
<%--                </p>--%>

            </div>
        </section>
    </div>
</main>
</body>
</html>
<%--<main class="main">--%>
<%--    <div class="container">--%>
<%--        <section class="section">--%>
<%--            <form class="section__form login__form" action="${pageContext.request.contextPath}/controller?command=login"--%>
<%--                  method="post">--%>
<%--                <input class="form__input login" type="text" placeholder="Username" name="username"/>--%>
<%--                <input class="form__input login" type="password" placeholder="Password" name="password"/>--%>
<%--                <button class="form__button" type="submit">Log in</button>--%>
<%--            </form>--%>
<%--            <p class="about__text login__text">--%>
<%--                Lorem ipsum dolor sit--%>
<%--                amet, consectetur dipiscing elit,--%>
<%--                sed do--%>
<%--                eiusmod tempor incididunt--%>
<%--                ut labore et dolore magna aliqua.--%>
<%--                Lorem ipsum dolor sit--%>
<%--                amet, consectetur dipiscing elit,--%>
<%--                sed do--%>
<%--                eiusmod tempor incididunt--%>
<%--                ut labore et dolore magna aliqua.--%>
<%--                Lorem ipsum dolor sit--%>
<%--                Lorem ipsum dolor sit--%>
<%--                amet, consectetur dipiscing elit,--%>
<%--                sed do--%>
<%--                eiusmod tempor incididunt--%>
<%--                ut labore et dolore magna aliqua.--%>
<%--                Lorem ipsum dolor sit--%>
<%--                amet, consectetur dipiscing elit,--%>
<%--                sed do--%>
<%--                eiusmod tempor incididunt--%>
<%--                ut labore et dolore magna aliqua.--%>
<%--                Lorem ipsum dolor sit--%>
<%--            </p>--%>
<%--            <c:if test="${errorMessage != null}">--%>
<%--                <div>--%>
<%--                    <h2 style="color: darkred">${errorMessage}</h2>--%>
<%--                </div>--%>
<%--            </c:if>--%>
<%--        </section>--%>
<%--    </div>--%>
<%--</main>--%>
