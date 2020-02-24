import {URL} from "../constants.js";
import {deleteRequest, submitRequest} from "../request.js";

const $createBtn = $('.create-btn');
const $nameInput = $('.name-input');
const $categorySelector = $('#category-selector');
const $subCategories = $('#subcategories');

const appendCategoryToSelect = category => {
    $categorySelector.append(`
        <option value="${category.id}">${category.name}</option>
    `);
};

const appendCategoriesToSelect = categories => {
    for (const category of categories) {
        appendCategoryToSelect(category);
    }
};

const activateCategorySelector = categories => {
    appendCategoriesToSelect(categories);
    $('select').formSelect();
};

const appendSubCategoryToTable = subCategory => {
    $subCategories.append(`
        <tr>
            <td>${subCategory.id}</td>
            <td>${subCategory.name}</td>
            <td>${subCategory.categoryName}</td>
            <td>
                <button value="${subCategory.id}" class="btn update-btn waves-effect waves-light">
                    update
                </button>
                <button value="${subCategory.id}" class="btn delete-btn waves-effect waves-light red">
                    delete
                </button>
            </td>
        </tr>
    `);
};

const appendSubCategoriesToTable = subCategories => {
    for (const subCategory of subCategories) {
        appendSubCategoryToTable(subCategory);
    }
};

const actionOnUpdate = () => {
    $('.update-btn').click((e) => {
        const id = e.target.value;

        $.ajax({
            url: `${URL}/sub_category/${id}`,
            type: 'get',
            success: response => {
                $createBtn.attr('value', id);
                $nameInput.val(response.name);

                if (response.category) {
                    $categorySelector.val(response.category.id);
                }
            }
        });
    });
};

const sendRequest = () => {
    const request = {
        name: $nameInput.val(),
        categoryId: $categorySelector.val()
    };

    submitRequest('sub_category', request);
};

$(document).ready(() => {
    $.ajax({
        url: `${URL}/category`,
        type: 'get',
        success: activateCategorySelector,
        error: console.log
    });

    $.ajax({
        url: `${URL}/sub_category`,
        type: 'get',
        success: response => {
            appendSubCategoriesToTable(response);
            actionOnUpdate();
            deleteRequest('sub_category');
        },
        error: console.log
    });

    $createBtn.click(sendRequest);
});