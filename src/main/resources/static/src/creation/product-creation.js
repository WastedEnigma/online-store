import {URL} from "../constants.js";
import {deleteRequest, submitRequest} from "../request.js";

const $nameInput = $('.name-input');
const $priceInput = $('.price-input');
const $descriptionInput = $('.description-input');
const $availableCheckbox = $('.available-checkbox');
const $createBtn = $('.create-btn');

const $categorySelect = $('#category-select');
const $modelSelect = $('#model-select');
const $fileSelect = $('.file-select');

const $products = $('#products');

const getBase64FromFile = file => {
    return new Promise((resolve, reject) => {
        const reader = new FileReader();
        reader.readAsDataURL(file);
        reader.onload = () => resolve(reader.result);
        reader.onerror = error => reject(error);
    });
};

const appendCategoryToSelect = category => {
    const subCategoryArr = category.subCategories;
    let subCategoriesStr = '';

    for (const subCategory of subCategoryArr) {
        subCategoriesStr += `
            <option id="sub-category-option" value="${subCategory.id}">
                ${subCategory.name}
            </option>
       `;
    }

    $categorySelect.append(`
        <optgroup label="${category.name}">
            ${subCategoriesStr}
        </optgroup>
   `);
};

const appendCategoriesToSelect = categories => {
    for (const category of categories) {
        appendCategoryToSelect(category);
    }
};

const activateCategorySelect = categories => {
    appendCategoriesToSelect(categories);
    $('#category-select').formSelect();
};

const appendModelToSelect = model => {
    $modelSelect.append(`
        <option value="${model.id}">${model.name}</option>
    `);
};

const appendModelsToSelect = models => {
    for (const model of models) {
        appendModelToSelect(model);
    }
};

const activateModelSelect = models => {
    appendModelsToSelect(models);
    $('#model-select').formSelect();
};


const appendProductsToTable = products => {
    for (const product of products) {
        appendProductToTable(product);
    }
};

const appendProductToTable = product => {
    const imgURL = product.image ? `${URL}/img/${product.image}` : "https://www.teliacompany.com/Assets/Images/not-available.png";

    $products.append(`
         <tr>
            <td>${product.id}</td>
            <td>${product.name}</td>
            <td>${product.price}</td>
            <td>${product.description}</td>
            <td>${product.available}</td>
            <td>${product.subCategoryName}</td>
            <td>${product.modelName}</td>
            <td>
                <img class="hoverable" src=${imgURL} style="width: 100%">
            </td>
            <td>
                <button value="${product.id}" class="btn update-btn waves-effect waves-light">
                    update
                </button>
                <button value="${product.id}" class="btn delete-btn waves-effect waves-light red">
                    delete
                </button>
            </td>
        </tr>
    `);
};

const actionOnUpdate = () => {
    $('.update-btn').click(e => {
        const id = e.target.value;

        $.ajax({
            url: `${URL}/product/${id}`,
            type: 'get',
            success: response => {
                $createBtn.attr('value', id);
                $nameInput.val(response.name);
                $priceInput.val(response.price);
                $descriptionInput.val(response.description);
                $availableCheckbox.val(response.available);

                if (response.available) {
                    $availableCheckbox.attr('checked', 'checked');
                }

                if (response.subCategory) {
                    $categorySelect.val(response.subCategory.id);
                }

                if (response.model) {
                    $modelSelect.val(response.model.id);
                }
            }
        });
    });
};

const sendRequest = () => {
    const request = {
        name: $nameInput.val(),
        price: $priceInput.val(),
        modelId: $modelSelect.val(),
        description: $descriptionInput.val(),
        available: $availableCheckbox.is(':checked'),
        subCategoryId: $('option:selected').val()
    };

    if (request.price <= 0) {
        alert("Price value should be more than 0 or not empty.");
        return;
    }

    getBase64FromFile($fileSelect[0].files[0])
        .then(data => request.data = data)
        .catch(() => request.data = null)
        .finally(() => {
            submitRequest('product', request);
        });
};

$(document).ready(() => {
    $.ajax({
        url: `${URL}/category`,
        type: 'get',
        success: activateCategorySelect,
        error: console.log
    });

    $.ajax({
        url: `${URL}/model`,
        type: 'get',
        success: activateModelSelect,
        error: console.log
    });

    $.ajax({
        url: `${URL}/product`,
        type: 'get',
        success: response => {
            appendProductsToTable(response);
            actionOnUpdate();
            deleteRequest('product');
        }
    });

    $createBtn.click(sendRequest);
});