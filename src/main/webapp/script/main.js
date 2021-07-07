"use strict"

const dark = "dark";
const theme = "theme";
const html = "html";
const languageSign = ".dropbutton";
const themeSign = ".moon";
const errorMessage = "A mistake occurred: ";
const showClass = "show";
const dropClass = "drop";
const displayBlock = "block";
const displayNone = "none";
const languageIconDark = "images/icons/lang_dark.png";
const languageIconLight = "images/icons/lang_light.png";
const sunIcon = "images/icons/sun.png";
const moonIcon = "images/icons/sun.png";
const errorInputClass = "error_input";

function changeTheme() {
    if (localStorage.getItem(theme) === dark) {
        localStorage.removeItem(theme);
    } else {
        localStorage.setItem(theme, dark);
    }

    makeDark();
}

function makeDark() {
    try {
        if (localStorage.getItem(theme) === dark) {
            document.querySelector(html).classList.add(dark);

            document.querySelector(languageSign).src = languageIconDark;
            document.querySelector(themeSign).src = sunIcon;
        } else {
            document.querySelector(html).classList.remove(dark);

            document.querySelector(languageSign).src = languageIconLight;
            document.querySelector(themeSign).src = moonIcon;
        }
    } catch (err) {
        console.log(errorMessage + err);
    }
}

makeDark();

function dropdown() {
    document.getElementById(dropClass).classList.toggle(showClass);
}

window.onclick = function (e) {
    if (!e.target.matches(languageSign)) {
        let myDropdown = document.getElementById(dropClass);

        if (myDropdown.classList.contains(showClass)) {
            myDropdown.classList.remove(showClass);
        }
    }
}

function validateLoginForm() {
    const pattern = /[a-zA-Z1-9]{5,50}/
    const errorLogin = document.getElementById("errorLogin");
    const errorPassword = document.getElementById("errorPassword");
    const loginField = document.getElementById("login");
    const passwordField = document.getElementById("password");
    let correctLogin = false;
    let correctPassword = false;

    if (!pattern.test(loginField.value)) {
        loginField.classList.add(errorInputClass);
        errorLogin.style.display = displayBlock;
        correctLogin = false;
    } else {
        loginField.classList.remove(errorInputClass);
        errorLogin.style.display = displayNone;
        correctLogin = true;
    }

    if (!pattern.test(passwordField.value)) {
        passwordField.classList.add(errorInputClass);
        errorPassword.style.display = displayBlock;
        correctPassword = false;
    } else {
        passwordField.classList.remove(errorInputClass);
        errorPassword.style.display = displayNone;
        correctPassword = true;
    }

    return correctLogin && correctPassword;
}

function validateBalanceForm() {
    const balance = document.getElementById("balance");
    const errorBalance = document.getElementById("errorBalance");
    const balanceValue = balance.value;

    if (isNaN(balanceValue) || parseFloat(balanceValue) < 0.00) {
        errorBalance.style.display = displayBlock;
        balance.classList.add(errorInputClass);

        return false;
    } else {
        errorBalance.style.display = displayNone;
        balance.classList.remove(errorInputClass);

        return true;
    }
}

function validateHotelForm() {
    const namePattern = /[a-zA-Z_ ]{5,50}/
    const descriptionPattern = /[a-zA-Z1-9_ ]{1,256}/
    const name = document.getElementById("nameOfHotel");
    const desc = document.getElementById("descOfHotel");
    const errorHotel = document.getElementById("errorHotel");
    const errorDesc = document.getElementById("errorDesc");
    let nameMatches = false;
    let descriptionMatches = false;

    if (!namePattern.test(name.value)) {
        name.classList.add("error_input");
        errorHotel.style.display = displayBlock;
        nameMatches = false;
    } else {
        name.classList.remove(errorInputClass);
        errorHotel.style.display = displayNone;
        nameMatches =  true;
    }

    if (!descriptionPattern.test(name.value)) {
        desc.classList.add(errorInputClass);
        errorDesc.style.display = displayBlock;
        descriptionMatches = false;
    } else {
        desc.classList.remove(errorInputClass);
        errorDesc.style.display = displayNone;
        descriptionMatches = true;
    }

    return nameMatches && descriptionMatches;
}

function validateRoomForm() {
    const places = document.getElementById("places");
    const errorPlaces = document.getElementById("errorPlaces");
    let correctPlaces = false;

    if (isNaN(places.value) || parseInt(places.value) < 1) {
        places.classList.add(errorInputClass);
        errorPlaces.style.display = displayBlock;
        correctPlaces = false;
    } else {
        places.classList.remove(errorInputClass);
        errorPlaces.style.display = displayNone;
        correctPlaces = true;
    }

    return correctPlaces;
}

function validateBookingForm() {
    const places = document.getElementById("places");
    const errorPlaces = document.getElementById("errorPlaces");
    const arrival = document.getElementById("arr");
    const departure = document.getElementById("dep");
    const errorDates = document.getElementById("errorDates");
    const arrivalValue = new Date(arrival.value);
    const departureValue = new Date(departure.value);
    let correctPlaces = false;
    let correctDates = false;
    let today = new Date();

    if (isNaN(places.value) || parseInt(places.value) < 1) {
        places.classList.add(errorInputClass);
        errorPlaces.style.display = displayBlock;
        correctPlaces = false;
    } else {
        places.classList.remove(errorInputClass);
        errorPlaces.style.display = displayNone;
        correctPlaces = true;
    }

    if ((arrivalValue > departureValue) || (arrivalValue < today) || (departureValue < today)) {
        arrival.classList.add(errorInputClass);
        departure.classList.add(errorInputClass);
        errorDates.style.display = displayBlock;
        correctDates = false;
    } else {
        arrival.classList.remove(errorInputClass);
        departure.classList.remove(errorInputClass);
        errorDates.style.display = displayNone;
        correctDates = true;
    }

    return correctPlaces && correctDates;
}

function validateSignUpForm() {
    const pattern = /[a-zA-Z1-9]{5,50}/
    const errorLogin = document.getElementById("errorLogin");
    const errorPassword = document.getElementById("errorPassword");
    const passwordsAreNotTheSame = document.getElementById("errorPasswordTheSame");
    const loginField = document.getElementById("signup");
    const passwordFirstField = document.getElementById("passwordFirst");
    const passwordSecondField = document.getElementById("passwordSecond");
    let correctLogin = false;
    let correctPassword = false;
    let passwordsAreTheSame = false;

    if (!pattern.test(loginField.value)) {
        loginField.classList.add(errorInputClass);
        errorLogin.style.display = displayBlock;
        correctLogin = false;
    } else {
        loginField.classList.remove(errorInputClass);
        errorLogin.style.display = displayNone;
        correctLogin = true;
    }

    if (!pattern.test(passwordFirstField.value) || !pattern.test(passwordSecondField.value)) {
        passwordFirstField.classList.add(errorInputClass);
        passwordSecondField.classList.add(errorInputClass);
        errorPassword.style.display = displayBlock;
        correctPassword = false;
    } else {
        passwordFirstField.classList.remove(errorInputClass);
        passwordSecondField.classList.remove(errorInputClass);
        errorPassword.style.display = displayNone;
        correctPassword = true;

        if (passwordFirstField.value === passwordSecondField.value) {
            passwordFirstField.classList.remove(errorInputClass);
            passwordSecondField.classList.remove(errorInputClass);
            passwordsAreNotTheSame.style.display = displayNone;
            passwordsAreTheSame = true;
        } else {
            passwordFirstField.classList.add(errorInputClass);
            passwordSecondField.classList.add(errorInputClass);
            passwordsAreNotTheSame.style.display = displayBlock;
            passwordsAreTheSame = false;
        }
    }

    return correctLogin && correctPassword && passwordsAreTheSame;
}