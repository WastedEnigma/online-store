import {URL} from "../constants.js";

const usernameInput = document.getElementById('username-input');
const passwordInput = document.getElementById('password-input');
const confirmPasswordInput = document.getElementById('confirm-password-input');

window.localStorage.setItem('username', '');
window.localStorage.setItem('onlineStoreToken', '');

const validatePassword = () => {
    if (usernameInput.value.toString().trim() === '') {
        usernameInput.setCustomValidity('Username should not be empty')
    }

    if (passwordInput.value !== confirmPasswordInput.value) {
        confirmPasswordInput.setCustomValidity('Passwords does not match');
    } else {
        confirmPasswordInput.setCustomValidity('');
    }

    if (passwordInput.value === '') {
        passwordInput.setCustomValidity('Password should not be empty');
    }
};

const $registerBtn = $('.register-btn');

$(document).ready(() => {
    passwordInput.onchange = validatePassword;
    confirmPasswordInput.onkeyup = validatePassword;

    $registerBtn.click(() => {
        const request = {
            username: usernameInput.value,
            password: passwordInput.value
        };

        $.ajax({
            url: `${URL}/user/register`,
            type: 'post',
            contentType: 'application/json',
            data: JSON.stringify(request),
            success: res => {
                window.localStorage.setItem('onlineStoreToken', res.token);
                window.localStorage.setItem('username', res.username);
                window.location.href = "/catalog";
            },
            error: console.log
        });
    });
});