package oleg.tokarenko.onlinestore.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HtmlPagesController {

    @RequestMapping("category-page")
    public String categoryCreation() {
        return "category-creation.html";
    }

    @RequestMapping("catalog")
    public String catalog() {
        return "catalog.html";
    }

    @RequestMapping("item")
    public String product() {
        return "product.html";
    }

    @RequestMapping("product-page")
    public String productCreation() {
        return "product-creation.html";
    }

    @RequestMapping("manufacturer-page")
    public String manufacturerCreation() {
        return "manufacturer-creation.html";
    }

    @RequestMapping("model-page")
    public String modelCreation() {
        return "model-creation.html";
    }

    @RequestMapping("country-page")
    public String countryCreation() {
        return "country-creation.html";
    }

    @RequestMapping("subcategory-page")
    public String subCategoryCreation() {
        return "subcategory-creation.html";
    }

    @RequestMapping("registration-page")
    public String registrationPage() {
        return "registration.html";
    }

    @RequestMapping("login-page")
    public String loginPage() {
        return "login.html";
    }

    @RequestMapping("favorite-products-page")
    public String favoriteProductsPage() {
        return "product-cart.html";
    }
}
