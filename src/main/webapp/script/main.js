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
    if(lightTheme === "true" && darkTheme === "false") {
        getDarkTheme();
        window.sessionStorage.setItem("lightTheme", "false");
        window.sessionStorage.setItem("darkTheme", "true");
    }
    if(lightTheme === "false" && darkTheme === "true") {
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
    for(let element of body) {
        element.style.backgroundColor = "var(--body-background-color-dark)";
        element.style.color = "var(--text-color-dark)";
    }
    for(let element of header) {
        element.style.backgroundColor = "var(--header-color-dark)";
    }
    for(let element of formButton) {
        element.style.backgroundColor = "var(--button-color-unhover-dark)";
        element.style.color = "var(--button-text-color-dark)";
    }
    for(let element of formInput) {
        element.style.backgroundColor = "var(--background-color-inputs)";
        element.style.color = "var(--input-text-color-dark)";
    }
    for(let element of cardDescriber) {
        element.style.backgroundColor = "var(--card-background-color-dark)";
    }
    for(let element of appInfo) {
        element.style.backgroundColor = "var(--background-color-inputs)";
    }
    for(let element of appButton) {
        element.style.backgroundColor = "var(--button-color-unhover-dark)";
    }
    for(let element of appActions) {
        element.style.backgroundColor = "var(--app-card-background-color-dark)";
    }
    for(let element of cardButton) {
        element.style.backgroundColor = "var(--button-color-unhover-dark)";
    }
    for(let element of footer) {
        element.style.backgroundColor = "var(--header-color-dark)";
    }
    for(let element of themeButton) {
        element.src = "images/icons/sun.png";
    }
    for(let element of dropDownItem) {
        element.style.backgroundColor = "var(--card-background-color-dark)";
    }
    for(let element of langIcon) {
        element.src = "images/icons/lang_dark.png";
    }
    for(let element of selection) {
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
    for(let element of body) {
        element.style.backgroundColor = "var(--body-background-color-light)";
        element.style.color = "var(--text-color-light)";
    }
    for(let element of header) {
        element.style.backgroundColor = "var(--header-color-light)";
    }
    for(let element of formButton) {
        element.style.backgroundColor = "var(--button-color-unhover-light)";
        element.style.color = "var(--button-text-color-light)";
    }
    for(let element of formInput) {
        element.style.backgroundColor = "var(--input-color-light)";
        element.style.color = "var(--input-text-color-light)";
    }
    for(let element of cardDescriber) {
        element.style.backgroundColor = "var(--card-background-color-light)";
    }
    for(let element of appInfo) {
        element.style.backgroundColor = "var(--card-background-color-light)";
    }
    for(let element of appButton) {
        element.style.backgroundColor = "var(--button-color-unhover-light)";
    }
    for(let element of appActions) {
        element.style.backgroundColor = "var(--app-card-background-color-light)";
    }
    for(let element of cardButton) {
        element.style.backgroundColor = "var(--button-color-unhover-light)";
    }
    for(let element of footer) {
        element.style.backgroundColor = "var(--header-color-light)";
    }
    for(let element of themeButton) {
        element.src = "images/icons/moon.png";
    }
    for(let element of dropDownItem) {
        element.style.backgroundColor = "var(--body-background-color-light)";
    }
    for(let element of langIcon) {
        element.src = "images/icons/lang_light.png";
    }
    for(let element of selection) {
        element.style.backgroundColor = "var(--input-color-light)";
        element.style.color = "var(--input-text-color-light)";
    }
}

function dropdown() {
    document.getElementById("drop").classList.toggle("show");
}

window.onclick = function(e) {
    if (!e.target.matches('.dropbutton')) {
        let myDropdown = document.getElementById("drop");
        if (myDropdown.classList.contains('show')) {
            myDropdown.classList.remove('show');
        }
    }
}