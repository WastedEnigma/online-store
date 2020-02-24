import {URL} from "../constants.js";
import {deleteRequest, submitRequest} from "../request.js";

const $nameInput = $('.name-input');
const $manufacturerSelect = $('#manufacturer-select');
const $models = $('#models');
const $createBtn = $('.create-btn');

const appendManufacturerToSelect = manufacturer => {
    $manufacturerSelect.append(`
        <option value="${manufacturer.id}">${manufacturer.name}</option>
    `);
};

const appendManufacturersToSelect = manufacturers => {
    for (const manufacturer of manufacturers) {
        appendManufacturerToSelect(manufacturer);
    }
};

const activateSelect = manufacturers => {
    appendManufacturersToSelect(manufacturers);
    $('select').formSelect();
};

const appendModelToTable = model => {
    $models.append(`
        <tr>
            <td>${model.id}</td>
            <td>${model.name}</td>
            <td>${model.manufacturerName}</td>
            <td>
                <button value="${model.id}" class="btn update-btn waves-effect waves-light">
                    update
                </button>
                <button value="${model.id}" class="btn delete-btn waves-effect waves-light red">
                    delete
                </button>
            </td>
        </tr>
    `);
};

const appendModelsToTable = models => {
    for (const model of models) {
        appendModelToTable(model);
    }
};

const actionOnUpdate = () => {
    $('.update-btn').click((e) => {
        const id = e.target.value;

        $.ajax({
            url: `${URL}/model/${id}`,
            type: 'get',
            success: response => {
                $createBtn.attr('value', id);
                $nameInput.val(response.name);

                if (response.manufacturer) {
                    $manufacturerSelect.val(response.manufacturer.id);
                }
            }
        });
    });
};

const sendRequest = () => {
    const request = {
        name: $nameInput.val(),
        manufacturerId: $manufacturerSelect.val()
    };

    submitRequest('model', request);
};

$(document).ready(() => {
    $.ajax({
        url: `${URL}/manufacturer`,
        type: 'get',
        success: activateSelect,
        error: console.log
    });

    $.ajax({
        url: `${URL}/model`,
        type: 'get',
        success: response => {
            appendModelsToTable(response);
            actionOnUpdate();
            deleteRequest('model');
        }
    });

    $createBtn.click(sendRequest);
});