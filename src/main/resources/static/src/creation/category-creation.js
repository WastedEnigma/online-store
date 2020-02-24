import {URL} from "../constants.js";
import {deleteRequest} from "../request.js";

getCategories();

function getCategories() {
    $.ajax({
        url: `${URL}/category`,
        type: 'get',
        success: response => {
            for (let category of response) {
                appendCategoryToTable(category);
            }

            deleteRequest('category');
            actionOnUpdate();
        }
    });
}

function appendCategoryToTable(category) {
    $('#categories').append(`
        <tr>
            <td class="category-id-col">${category.id}</td>
            <td class="category-name-col">${category.name}</td>
            <td>
                <button value="${category.id}" class="btn update-btn waves-effect waves-light">
                    update
                </button>
                <button value="${category.id}" class="btn delete-btn waves-effect waves-light red">
                    delete
                </button>
            </td>
        </tr>
    `);
}

function actionOnUpdate() {
    $('.update-btn').click(e => {
        let id = e.target.value;
        let $btn = $(e.target);

        $('#category-name').val($btn.parent().siblings('.category-name-col').html());
        $('#category-id').val(id);
        $('.modal').modal('open');
    });
}

$(document).ready(function() {
    $('.modal').modal();
});

$('#category-create-btn').click(() => {
    const request = {
        name: $('#category-name').val()
    };

    let id = $('#category-id');
    let typeOfRequest = id.val() !== '' ? 'put' : 'post';

    $.ajax({
        url: `${URL}/category${id.val() !== '' ? "?id=" + id.val() : ''}`,
        contentType: 'application/json',
        type: typeOfRequest,
        data: JSON.stringify(request),
        success: response => {
            console.log(typeOfRequest, response);
            location.reload();
        }
    });

    $('.modal').modal('close');
});

$('.modal-trigger').click(() => {
    $('#category-name').val('');
    $('#category-id').val('');
});
