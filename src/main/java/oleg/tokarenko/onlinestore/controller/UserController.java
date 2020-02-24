package oleg.tokarenko.onlinestore.controller;

import oleg.tokarenko.onlinestore.dto.request.UserRequest;
import oleg.tokarenko.onlinestore.dto.response.AuthenticationResponse;
import oleg.tokarenko.onlinestore.dto.response.product.ProductResponse;
import oleg.tokarenko.onlinestore.service.ProductService;
import oleg.tokarenko.onlinestore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Set;

@CrossOrigin
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private ProductService productService;

    @PostMapping("/login")
    public AuthenticationResponse login(@Valid @RequestBody UserRequest request) {
        return userService.login(request);
    }

    @PostMapping("/register")
    public AuthenticationResponse register(@Valid @RequestBody UserRequest request) {
        return userService.register(request);
    }

    @GetMapping("/checkToken")
    public void checkToken() {
    }

    @GetMapping("/favorite-products")
    public Set<ProductResponse> findAllByUsername() {
        return productService.findAllByUsername();
    }

    @PreAuthorize("authentication.principal == #text && hasAnyRole('USER', 'ADMIN')")
    @GetMapping("/test")
    public void test(String text) {
        System.out.println("find cart of " + text);
    }
}
