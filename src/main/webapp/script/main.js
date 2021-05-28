"use strict"

function checkForTheme() {
    const lightTheme = window.sessionStorage.getItem("lightTheme");
    const darkTheme = window.sessionStorage.getItem("darkTheme");
    if (lightTheme === "false" && darkTheme === "true") {
        getDarkTheme();
    }
    if (lightTheme === "true" && darkTheme === "false") {
        getLightTheme();
    }
}

function setUpTheme() {
    const lightTheme = window.sessionStorage.getItem("lightTheme");
    const darkTheme = window.sessionStorage.getItem("darkTheme");
    if (lightTheme === "true" && darkTheme === "false") {
        getDarkTheme();
        window.sessionStorage.setItem("lightTheme", "false");
        window.sessionStorage.setItem("darkTheme", "true");
    }
    if (lightTheme === "false" && darkTheme === "true") {
        getLightTheme();
        window.sessionStorage.setItem("lightTheme", "true");
        window.sessionStorage.setItem("darkTheme", "false");
    }
}

function getDarkTheme() {
    const body = document.getElementsByClassName("body");
    const header = document.getElementsByClassName("header");
    const formButton = document.getElementsByClassName("form__button");
    const formInput = document.getElementsByClassName("form__input");
    const cardDescriber = document.getElementsByClassName("card-describer");
    const appInfo = document.getElementsByClassName("app__info");
    const appButton = document.getElementsByClassName("app__button");
    const appActions = document.getElementsByClassName("app__actions");
    const cardButton = document.getElementsByClassName("card-button");
    const footer = document.getElementsByClassName("footer");
    const themeButton = document.getElementsByClassName("moon")
    const dropDownItem = document.getElementsByClassName("dropdown-link");
    const langIcon = document.getElementsByClassName("dropbutton");
    const selection = document.getElementsByClassName("classes");
    for (let element of body) {
        element.style.backgroundColor = "var(--body-background-color-dark)";
        element.style.color = "var(--text-color-dark)";
    }
    for (let element of header) {
        element.style.backgroundColor = "var(--header-color-dark)";
    }
    for (let element of formButton) {
        element.style.backgroundColor = "var(--button-color-unhover-dark)";
        element.style.color = "var(--button-text-color-dark)";
    }
    for (let element of formInput) {
        element.style.backgroundColor = "var(--background-color-inputs)";
        element.style.color = "var(--input-text-color-dark)";
    }
    for (let element of cardDescriber) {
        element.style.backgroundColor = "var(--card-background-color-dark)";
    }
    for (let element of appInfo) {
        element.style.backgroundColor = "var(--background-color-inputs)";
    }
    for (let element of appButton) {
        element.style.backgroundColor = "var(--button-color-unhover-dark)";
    }
    for (let element of appActions) {
        element.style.backgroundColor = "var(--app-card-background-color-dark)";
    }
    for (let element of cardButton) {
        element.style.backgroundColor = "var(--button-color-unhover-dark)";
    }
    for (let element of footer) {
        element.style.backgroundColor = "var(--header-color-dark)";
    }
    for (let element of themeButton) {
        element.src = "images/icons/sun.png";
    }
    for (let element of dropDownItem) {
        element.style.backgroundColor = "var(--card-background-color-dark)";
    }
    for (let element of langIcon) {
        element.src = "images/icons/lang_dark.png";
    }
    for (let element of selection) {
        element.style.backgroundColor = "var(--background-color-inputs)";
        element.style.color = "var(--input-text-color-dark)";
    }
}

function getLightTheme() {
    const body = document.getElementsByClassName("body");
    const header = document.getElementsByClassName("header");
    const formButton = document.getElementsByClassName("form__button");
    const formInput = document.getElementsByClassName("form__input");
    const cardDescriber = document.getElementsByClassName("card-describer");
    const appInfo = document.getElementsByClassName("app__info");
    const appButton = document.getElementsByClassName("app__button");
    const appActions = document.getElementsByClassName("app__actions");
    const cardButton = document.getElementsByClassName("card-button");
    const footer = document.getElementsByClassName("footer");
    const themeButton = document.getElementsByClassName("moon");
    const dropDownItem = document.getElementsByClassName("dropdown-link");
    const langIcon = document.getElementsByClassName("dropbutton");
    const selection = document.getElementsByClassName("classes");
    for (let element of body) {
        element.style.backgroundColor = "var(--body-background-color-light)";
        element.style.color = "var(--text-color-light)";
    }
    for (let element of header) {
        element.style.backgroundColor = "var(--header-color-light)";
    }
    for (let element of formButton) {
        element.style.backgroundColor = "var(--button-color-unhover-light)";
        element.style.color = "var(--button-text-color-light)";
    }
    for (let element of formInput) {
        element.style.backgroundColor = "var(--input-color-light)";
        element.style.color = "var(--input-text-color-light)";
    }
    for (let element of cardDescriber) {
        element.style.backgroundColor = "var(--card-background-color-light)";
    }
    for (let element of appInfo) {
        element.style.backgroundColor = "var(--card-background-color-light)";
    }
    for (let element of appButton) {
        element.style.backgroundColor = "var(--button-color-unhover-light)";
    }
    for (let element of appActions) {
        element.style.backgroundColor = "var(--app-card-background-color-light)";
    }
    for (let element of cardButton) {
        element.style.backgroundColor = "var(--button-color-unhover-light)";
    }
    for (let element of footer) {
        element.style.backgroundColor = "var(--header-color-light)";
    }
    for (let element of themeButton) {
        element.src = "images/icons/moon.png";
    }
    for (let element of dropDownItem) {
        element.style.backgroundColor = "var(--body-background-color-light)";
    }
    for (let element of langIcon) {
        element.src = "images/icons/lang_light.png";
    }
    for (let element of selection) {
        element.style.backgroundColor = "var(--input-color-light)";
        element.style.color = "var(--input-text-color-light)";
    }
}

function dropdown() {
    document.getElementById("drop").classList.toggle("show");
}

window.onclick = function (e) {
    if (!e.target.matches(".dropbutton")) {
        let myDropdown = document.getElementById("drop");
        if (myDropdown.classList.contains("show")) {
            myDropdown.classList.remove("show");
        }
    }
}

function validateLoginForm() {
    const pattern = /[a-zA-Z1-9]{1,50}/
    const errorLogin = document.getElementById("errorLogin");
    const errorPassword = document.getElementById("errorPassword");
    const loginField = document.getElementById("login");
    const passwordField = document.getElementById("password");
    let correctLogin = false;
    let correctPassword = false;
    if (!loginField.value.match(pattern)) {
        loginField.className = "form__input login form__input-login error_input";
        errorLogin.style.display = "block";
        correctLogin = false;
    } else {
        loginField.className = "form__input login form__input-login";
        errorLogin.style.display = "none";
        correctLogin = true;
    }
    if (!passwordField.value.match(pattern)) {
        passwordField.className = "form__input login form__input-login error_input";
        errorPassword.style.display = "block";
        correctPassword = false;
    } else {
        passwordField.className = "form__input login form__input-login";
        errorPassword.style.display = "none";
        correctPassword = true;
    }
    return correctLogin && correctPassword;
}

function validateBalanceForm() {
    const balance = document.getElementById("balance");
    const errorBalance = document.getElementById("errorBalance");
    const balanceValue = balance.value;
    if (isNaN(balanceValue) || parseFloat(balanceValue) < 1) {
        errorBalance.style.display = "block";
        balance.className = "form__input login form__input-login error_input";
        return false;
    } else {
        errorBalance.style.display = "none";
        balance.className = "form__input login form__input-login";
        return true;
    }
}

function validateHotelForm() {
    const pattern = /[a-zA-Z]{1,50}/
    const name = document.getElementById("nameOfHotel");
    const errorHotel = document.getElementById("errorHotel");
    if (!name.value.match(pattern)) {
        name.className = "form__input error_input";
        errorHotel.style.display = "block";
        return false;
    } else {
        name.className = "form__input";
        errorHotel.style.display = "none";
        return false;
    }
}

function validateRoomForm() {
    const pattern = /[a-zA-Z]{1,50}/
    const name = document.getElementById("nameOfHotel");
    const errorHotel = document.getElementById("errorHotelName");
    const places = document.getElementById("places");
    const errorPlaces = document.getElementById("errorPlaces");
    let correctName = false;
    let correctPlaces = false;
    if (!name.value.match(pattern)) {
        name.className = "form__input error_input";
        errorHotel.style.display = "block";
        correctName = false;
    } else {
        name.className = "form__input";
        errorHotel.style.display = "none";
        correctName = true;
    }
    if (isNaN(places.value) || parseInt(places.value) < 1) {
        places.className = "form__input error_input";
        errorPlaces.style.display = "block";
        correctPlaces = false;
    } else {
        places.className = "form__input";
        errorPlaces.style.display = "none";
        correctPlaces = true;
    }
    return correctName && correctPlaces;
}

function validateBookingForm() {
    const pattern = /[a-zA-Z]{1,50}/
    const name = document.getElementById("nameOfHotel");
    const errorHotel = document.getElementById("errorHotel");
    const places = document.getElementById("places");
    const errorPlaces = document.getElementById("errorPlaces");
    const arrival = document.getElementById("arr");
    const departure = document.getElementById("dep");
    const errorDates = document.getElementById("errorDates");
    const arrivalValue = new Date(arrival.value);
    const departureValue = new Date(departure.value);
    let correctName = false;
    let correctPlaces = false;
    let correctDates = false;
    let today = new Date();
    if (!name.value.match(pattern)) {
        name.className = "form__input error_input";
        errorHotel.style.display = "block";
        correctName = false;
    } else {
        name.className = "form__input";
        errorHotel.style.display = "none";
        correctName = true;
    }
    if (isNaN(places.value) || parseInt(places.value) < 1) {
        places.className = "form__input error_input";
        errorPlaces.style.display = "block";
        correctPlaces = false;
    } else {
        places.className = "form__input";
        errorPlaces.style.display = "none";
        correctPlaces = true;
    }
    if ((arrivalValue > departureValue) || (arrivalValue < today) || (departureValue < today)) {
        arrival.className = "form__input error_input";
        departure.className = "form__input error_input";
        errorDates.style.display = "block";
        correctDates = false;
    } else {
        arrival.className = "form__input";
        departure.className = "form__input";
        errorDates.style.display = "none";
        correctDates = true;
    }
    return correctName && correctPlaces && correctDates;
}