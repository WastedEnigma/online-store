import {URL} from "../constants.js";

const $usernameContainer = $('.username-container');

if (!window.localStorage.getItem('onlineStoreToken')) {
    window.location.href = "/login-page";
} else {
    $usernameContainer.append(
        `<i class="material-icons right">person_pin</i>
        <strong>${window.localStorage.getItem('username')}</strong>`
    );

    document.getElementById('cart').style.display = 'block';
}

/*$.ajax({
    url: `${URL}/user/checkToken`,
    type: 'get',
    success: (res) => {

    },
    error: (res) => {

    }
});*/

if (window.localStorage.getItem('username') === 'Oleg Tokarenko') {
    document.getElementById('product-page').style.display = 'block';
    document.getElementById('cart').style.display = 'none';

    if (document.getElementById('cart-btn') !== null) {
        document.getElementById('cart-btn').style.display = 'none';
    }
}

