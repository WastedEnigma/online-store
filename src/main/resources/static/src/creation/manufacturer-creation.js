import {URL} from "../constants.js";
import {deleteRequest, submitRequest} from "../request.js";

const $nameInput = $('.name-input');
const $countrySelect = $('#country-select');
const $manufacturers = $('#manufacturers');
const $createBtn = $('.create-btn');

const appendCountryToSelect = country => {
    $countrySelect.append(`
        <option value="${country.id}">${country.name}</option>
    `);
};

const appendCountriesToSelect = countries => {
    for (const country of countries) {
        appendCountryToSelect(country);
    }
};

const activateSelect = countries => {
    appendCountriesToSelect(countries);
    $('select').formSelect();
};

const appendManufacturerToTable = manufacturer => {
    $manufacturers.append(`
        <tr>
            <td>${manufacturer.id}</td>
            <td>${manufacturer.name}</td>
            <td>${manufacturer.countryName}</td>
            <td>
                <button value="${manufacturer.id}" class="btn update-btn waves-effect waves-light">
                    update
                </button>
                <button value="${manufacturer.id}" class="btn delete-btn waves-effect waves-light red">
                    delete
                </button>
            </td>
        </tr>
    `);
};

const appendManufacturersToTable = manufacturers => {
    for (const manufacturer of manufacturers) {
        appendManufacturerToTable(manufacturer);
    }
};

const actionOnUpdate = () => {
    $('.update-btn').click((e) => {
        const id = e.target.value;

        $.ajax({
            url: `${URL}/manufacturer/${id}`,
            type: 'get',
            success: response => {
                $createBtn.attr('value', id);
                $nameInput.val(response.name);

                if (response.country) {
                    $countrySelect.val(response.country.id);
                }
            }
        });
    });
};

const sendRequest = () => {
    const request = {
        name: $nameInput.val(),
        countryId: $countrySelect.val()
    };

    submitRequest('manufacturer', request);
};

$(document).ready(function () {
    $.ajax({
        url: `${URL}/country`,
        type: 'get',
        success: activateSelect,
        error: console.log
    });

    $.ajax({
        url: `${URL}/manufacturer`,
        type: 'get',
        success: response => {
            appendManufacturersToTable(response);
            deleteRequest('manufacturer');
            actionOnUpdate();
        },
        error: console.log
    });

    $createBtn.click(sendRequest);
});