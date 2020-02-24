package oleg.tokarenko.onlinestore.controller;

import oleg.tokarenko.onlinestore.dto.request.ProductForCartRequest;
import oleg.tokarenko.onlinestore.dto.response.CartResponse;
import oleg.tokarenko.onlinestore.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @PostMapping
    public void addProductToCart(@RequestBody ProductForCartRequest request) {
        cartService.addProductToCart(request);
    }

    @GetMapping
    public CartResponse getCart() {
        return cartService.getCart();
    }
}
