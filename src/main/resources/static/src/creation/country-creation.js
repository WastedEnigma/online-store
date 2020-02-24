import {URL} from "../constants.js";
import {deleteRequest, submitRequest} from "../request.js";

const $nameInput = $('.name-input');
const $createBtn = $(".create-btn");
const $countries = $('#countries');

const appendCountriesToTable = countries => {
    for (const country of countries) {
        appendCountryToTable(country);
    }
};

const appendCountryToTable = country => {
    $countries.append(`
        <tr>
            <td class="country-id-col">${country.id}</td>
            <td class="country-name-col">${country.name}</td>
            <td>
                <button value="${country.id}" class="btn update-btn waves-effect waves-light">
                    update
                </button>
                <button value="${country.id}" class="btn delete-btn waves-effect waves-light red">
                    delete
                </button>
            </td>
        </tr>
    `);
};

const actionOnUpdate = () => {
    $('.update-btn').click((e) => {
        const id = e.target.value;

        $.ajax({
            url: `${URL}/country/${id}`,
            type: 'get',
            success: response => {
                $createBtn.attr('value', id);
                $nameInput.val(response.name);
            }
        });
    });
};

const sendRequest = () => {
    const request = {
        name: $nameInput.val()
    };

    submitRequest('country', request);
};

$(document).ready(() => {
    $.ajax({
        url: `${URL}/country`,
        type: 'get',
        success: response => {
            appendCountriesToTable(response);
            deleteRequest('country');
            actionOnUpdate();
        }
    });

    $createBtn.click(sendRequest);
});


