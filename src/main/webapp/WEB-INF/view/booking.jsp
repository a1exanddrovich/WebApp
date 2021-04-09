<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Booking</title>
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
                <form class="section__form" action="" method="post">
                    <input class="form__input" type="text" placeholder="Hotel name" autofocus>
                    <input class="form__input" type="text" placeholder="Class">
                    <input class="form__input" type="text" placeholder="Room">
                    <input class="form__input" type="date" placeholder="Arriving ate">
                    <input class="form__input" type="date" placeholder="Departure date">
                    <button class="form__button button-hover" type="submit">Book it</button>
                </form>
                <div class="section__image"></div>
            </div>
        </section>
    </div>
</main>
<jsp:include page="fragments/footer.jsp"/>
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
