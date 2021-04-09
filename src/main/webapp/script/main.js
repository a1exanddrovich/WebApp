"use strict"

// let themeIsDark = false;

function checkForDarkness() {
    if(window.sessionStorage.getItem("lightTheme") === "false" && window.sessionStorage.getItem("darkTheme") === "true") {
        changeTheme();
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
    const themeButton = document.getElementsByClassName("theme__image");
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
    const themeButton = document.getElementsByClassName("theme__image");
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
}
//
// function changeTheme() {
//     const lightTheme = window.sessionStorage.getItem("lightTheme");
//     const darkTheme = window.sessionStorage.getItem("darkTheme");
//     const body = document.getElementsByClassName("body");
//     const header = document.getElementsByClassName("header");
//     const formButton = document.getElementsByClassName("form__button");
//     const formInput = document.getElementsByClassName("form__input");
//     const cardDescriber = document.getElementsByClassName("card-describer");
//     const appInfo = document.getElementsByClassName("app__info");
//     const appButton = document.getElementsByClassName("app__button");
//     const appActions = document.getElementsByClassName("app__actions");
//     const cardButton = document.getElementsByClassName("card-button");
//     const footer = document.getElementsByClassName("footer");
//     const themeButton = document.getElementsByClassName("theme__image");
//     if(lightTheme === "false" && darkTheme === "true") {
//         window.sessionStorage.setItem("lightTheme", "false");
//         window.sessionStorage.setItem("darkTheme", "true");
//         //themeIsDark = true;
//         for(let element of body) {
//             element.style.backgroundColor = "var(--body-background-color-dark)";
//             element.style.color = "var(--text-color-dark)";
//         }
//         for(let element of header) {
//             element.style.backgroundColor = "var(--header-color-dark)";
//         }
//         for(let element of formButton) {
//             element.style.backgroundColor = "var(--button-color-unhover-dark)";
//             element.style.color = "var(--button-text-color-dark)";
//         }
//         for(let element of formInput) {
//             element.style.backgroundColor = "var(--background-color-inputs)";
//             element.style.color = "var(--input-text-color-dark)";
//         }
//         for(let element of cardDescriber) {
//             element.style.backgroundColor = "var(--card-background-color-dark)";
//         }
//         for(let element of appInfo) {
//             element.style.backgroundColor = "var(--background-color-inputs)";
//         }
//         for(let element of appButton) {
//             element.style.backgroundColor = "var(--button-color-unhover-dark)";
//         }
//         for(let element of appActions) {
//             element.style.backgroundColor = "var(--app-card-background-color-dark)";
//         }
//         for(let element of cardButton) {
//             element.style.backgroundColor = "var(--button-color-unhover-dark)";
//         }
//         for(let element of footer) {
//             element.style.backgroundColor = "var(--header-color-dark)";
//         }
//         for(let element of themeButton) {
//             element.src = "images/icons/sun.png";
//         }
//     }
//      if (lightTheme === "true" && darkTheme === "false") {
//          window.sessionStorage.setItem("lightTheme", "true");
//          window.sessionStorage.setItem("darkTheme", "false");
//         //themeIsDark = false;
//         localStorage.setItem("theme", "day");
//         for(let element of body) {
//             element.style.backgroundColor = "var(--body-background-color-light)";
//             element.style.color = "var(--text-color-light)";
//         }
//         for(let element of header) {
//             element.style.backgroundColor = "var(--header-color-light)";
//         }
//         for(let element of formButton) {
//             element.style.backgroundColor = "var(--button-color-unhover-light)";
//             element.style.color = "var(--button-text-color-light)";
//         }
//         for(let element of formInput) {
//             element.style.backgroundColor = "var(--input-color-light)";
//             element.style.color = "var(--input-text-color-light)";
//         }
//         for(let element of cardDescriber) {
//             element.style.backgroundColor = "var(--card-background-color-light)";
//         }
//         for(let element of appInfo) {
//             element.style.backgroundColor = "var(--card-background-color-light)";
//         }
//         for(let element of appButton) {
//             element.style.backgroundColor = "var(--button-color-unhover-light)";
//         }
//         for(let element of appActions) {
//             element.style.backgroundColor = "var(--app-card-background-color-light)";
//         }
//         for(let element of cardButton) {
//             element.style.backgroundColor = "var(--button-color-unhover-light)";
//         }
//         for(let element of footer) {
//             element.style.backgroundColor = "var(--header-color-light)";
//         }
//         for(let element of themeButton) {
//             element.src = "images/icons/moon.png";
//         }
//     }
// }