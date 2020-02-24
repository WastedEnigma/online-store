import {URL} from "../constants.js";

const $usernameInput = $('#username-input');
const $passwordInput = $('#password-input');
const $loginBtn = $('.login-btn');

$(document).ready(() => {
    $loginBtn.click(() => {
        const request = {
            username: $usernameInput.val(),
            password: $passwordInput.val()
        };

        $.ajax({
            url: `${URL}/user/login`,
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

