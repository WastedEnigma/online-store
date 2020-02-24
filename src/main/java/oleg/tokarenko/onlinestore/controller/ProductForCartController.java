package oleg.tokarenko.onlinestore.controller;

import oleg.tokarenko.onlinestore.service.ProductForCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/product-for-cart")
public class ProductForCartController {

    @Autowired
    private ProductForCartService productForCartService;

    @DeleteMapping
    public void delete(Long id) {
        productForCartService.delete(id);
    }
}
