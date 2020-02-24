package oleg.tokarenko.onlinestore.service;

import oleg.tokarenko.onlinestore.dto.request.ProductForCartRequest;
import oleg.tokarenko.onlinestore.dto.response.CartResponse;
import oleg.tokarenko.onlinestore.entity.Cart;
import oleg.tokarenko.onlinestore.entity.ProductForCart;
import oleg.tokarenko.onlinestore.repository.CartRepository;
import oleg.tokarenko.onlinestore.repository.ProductForCartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ProductForCartRepository productForCartRepository;

    @Autowired
    private ProductService productService;

    public CartResponse getCart() {
        final String username = (String) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();

        final Cart cart = cartRepository.findCartByUsername(username)
                .orElseThrow(() -> new RuntimeException("User does not exist."));

        return new CartResponse(cart);
    }

    public void addProductToCart(ProductForCartRequest request) {
        final String username = (String) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();

        final Cart cart = cartRepository.findCartByUsername(username)
                .orElseThrow(() -> new RuntimeException("User does not exist."));

        productForCartRepository.save(
                ProductForCart.builder()
                    .cart(cart)
                    .product(productService.findOne(request.getProductId()))
                    .count(request.getCount())
                    .build()
        );
    }
}
