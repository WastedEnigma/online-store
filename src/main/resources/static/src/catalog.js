import {URL} from './constants.js';

const pageSize = $('#page-size');
const page = $('#page-number');
const container = $('.container');
const $subCategorySelector = $('#subcategory-selector');
const $findBtn = $('.find-btn');
const $minPriceInput = $('.min-price-input');
const $maxPriceInput = $('.max-price-input');
const $nameInput = $('.name-input');

let pages = 0;

const appendProductToContainer = product => {
    const imgURL = product.image ? `${URL}/img/${product.image}` : "https://www.teliacompany.com/Assets/Images/not-available.png";

    container.append(`
        <div class="item">
            <h4>${product.name}</h4>
            <img class="hoverable" src=${imgURL}>
            <p>$${product.price}</p>
            <a class="btn waves-effect waves-light" href="/item?id=${product.id}">Details</a>    
        </div>
    `);
};

const appendProductsToContainer = products => {
    container.html('');

    for (const product of products) {
        appendProductToContainer(product);
    }
};

const getProductFilter = () => {
    const id = $subCategorySelector.children()
        .children(':selected')
        .attr('value');

    const reqSubCategoryStr = id !== undefined ? `&subCategoryId=${id}` : '';

    let reqPriceStr = '';

    if ($minPriceInput.val() != null && $maxPriceInput.val() != null) {
        reqPriceStr = `minPrice=${$minPriceInput.val()}&maxPrice=${$maxPriceInput.val()}&`;
    } else if ($minPriceInput.val() != null) {
        reqPriceStr = `minPrice=${$minPriceInput.val()}&`;
    } else if ($maxPriceInput.val() != null) {
        reqPriceStr = `maxPrice=${$maxPriceInput.val()}&`;
    }

    $.ajax({
        url: `${URL}/product/filter?` + reqPriceStr
        + `paginationRequest.page=${page.html()}`
        + `&paginationRequest.size=${pageSize.val()}`
        + `&paginationRequest.fields=${['name', 'price']}&paginationRequest.direction=ASC`
        + `&name=${$nameInput.val()}`
        + reqSubCategoryStr,
        type: 'get',
        success: res => {
            appendProductsToContainer(res.data);
            pages = res.totalPages;
        },
        error: console.log
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

    $subCategorySelector.append(`
        <optgroup label="${category.name}">
            ${subCategoriesStr}
        </optgroup>
   `);
};

$(document).ready(() => {
    $('select').formSelect();
});

const appendCategoriesToSelect = categories => {
    for (const category of categories) {
        appendCategoryToSelect(category);
    }
};

const activateCategorySelect = categories => {
    appendCategoriesToSelect(categories);
    $('#subcategory-selector').formSelect();
};

$(document).ready(() => {
    $.ajax({
        url: `${URL}/category`,
        type: 'get',
        success: activateCategorySelect,
        error: console.log
    });

    $('.btn-next').click(() => {
        let currentPage = +page.html();

        if (currentPage < pages - 1) {
            page.html(currentPage + 1);
            getProductFilter();
        }
    });

    $('.btn-previous').click(() => {
        let currentPage = +page.html();

        if (currentPage > 0) {
            page.html(currentPage - 1);
            getProductFilter();
        }
    });

    $findBtn.click(getProductFilter);
    $nameInput.keyup(getProductFilter);

    getProductFilter();
});
