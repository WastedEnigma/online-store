package oleg.tokarenko.onlinestore.controller;

import oleg.tokarenko.onlinestore.dto.request.ProductForOrderRequest;
import oleg.tokarenko.onlinestore.dto.response.ProductForOrderResponse;
import oleg.tokarenko.onlinestore.service.ProductForOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/product_for_order")
@CrossOrigin
public class ProductForOrderController {

    @Autowired
    private ProductForOrderService productForOrderService;

    @PostMapping
    public void save(@RequestBody @Valid ProductForOrderRequest request) {
        productForOrderService.save(request);
    }

    @PutMapping
    public void update(Long id, @RequestBody @Valid ProductForOrderRequest request) {
        productForOrderService.update(request, id);
    }

    @DeleteMapping
    public void delete(Long id) {
        productForOrderService.delete(id);
    }

    @GetMapping
    public List<ProductForOrderResponse> findAll() {
        return productForOrderService.findAll();
    }
}
