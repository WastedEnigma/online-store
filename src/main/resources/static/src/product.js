import {URL} from "./constants.js";

const searchParams = new URLSearchParams(window.location.search);
const $addToFavoriteBtn = $('.add-to-favorite-btn');

const setDataFromApi = data => {
    const imgURL = data.image ? `${URL}/img/${data.image}` : "https://www.teliacompany.com/Assets/Images/not-available.png";

    $('.title').html(data.name);

    $('.container').append(`
        <div style="padding: 20px" class="card">
            <div class="photo-container">
                <img class="photo" src=${imgURL}>
            </div>
            <div class="item-description">
                <p class="item-price"><b>Price:</b> $${data.price}</p>
                <p class="item-text">${data.description}</p>
                <p class="item-model"><b>Model:</b> ${data.modelName}</p>
            </div>
        </div>
    `);
};

$(document).ready(() => {
    $.ajax({
        url: `${URL}/product/byId?id=${searchParams.get('id')}`,
        type: 'get',
        success: res => {
            setDataFromApi(res);
        },
        error: console.log
    });

    $addToFavoriteBtn.click(() => {
        const request = {
            productId: searchParams.get('id'),
            count: 1
        };

        $.ajax({
            url: `${URL}/cart`,
            type: 'post',
            headers: {
                "Authorization": "Bearer " + window.localStorage.getItem('onlineStoreToken')
            },
            contentType: 'application/json',
            data: JSON.stringify(request),
            success: res => {
                console.log('success', res);
            },
            error: console.log
        });
    });
});

