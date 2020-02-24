import {URL} from "./constants.js";

const $collection = $('.collection');

const appendProductsToContainer = res => {
    for (const product of res) {
        appendProductToContainer(product);
    }
};

const appendProductToContainer = res => {
    const imgURL = res.productImage ? `${URL}/img/${res.productImage}` : "https://www.teliacompany.com/Assets/Images/not-available.png";

    $collection.append(`
        <li class="collection-item">
            <img class="hoverable" src=${imgURL} width="10%">
            ${res.productName}
            <a class="secondary-content">
                <button class="btn del-btn waves-effect waves-light red" value=${res.id}>Delete</button>
            </a>
        </li>
    `);
};

const deleteProductForCart = () => {
    $('.del-btn').click((e) => {
        const id = e.target.value;

        $.ajax({
            url: `${URL}/product-for-cart?id=${id}`,
            type: 'delete',
            headers: {
                "Authorization": "Bearer " + window.localStorage.getItem('onlineStoreToken')
            },
            success: () => {
                $(e.target.parentElement.parentElement).fadeOut();
                console.log('deleted product-for-cart with id', id);
            }
        });
    });
};

$(document).ready(() => {
    $.ajax({
        url: `${URL}/cart`,
        type: 'get',
        headers: {
            "Authorization": "Bearer " + window.localStorage.getItem('onlineStoreToken')
        },
        success: res => {
            appendProductsToContainer(res.productForCarts);
            deleteProductForCart();
        },
        error: console.log
    });
});