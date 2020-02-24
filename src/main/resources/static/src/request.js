import {URL} from "./constants.js";

export const deleteRequest = (link) => {
    $('.delete-btn').click(e => {
        const id = e.target.value;

        const deletePrompt = confirm('Are you sure you want to delete this ' + link + '?');

        if (deletePrompt) {
            $.ajax({
                url: `${URL}/${link}?id=${id}`,
                type: 'delete',
                headers: {
                    "Authorization": "Bearer " + window.localStorage.getItem('onlineStoreToken')
                },
                success: () => {
                    $(e.target.parentElement.parentElement).fadeOut();
                    console.log('deleted ' + link + ' with id', id);
                }
            });
        }
    });
};

export const submitRequest = (link, request) => {
    const id = $('.create-btn').attr('value');

    const requestType = id ? 'put' : 'post';

    $.ajax({
        url: `${URL}/${link}${id ? '?id=' + id : ''}`,
        type: requestType,
        headers: {
            "Authorization": "Bearer " + window.localStorage.getItem('onlineStoreToken')
        },
        contentType: 'application/json',
        data: JSON.stringify(request),
        success: res => {
            console.log(requestType, res);
            location.reload();
        }
    });
};