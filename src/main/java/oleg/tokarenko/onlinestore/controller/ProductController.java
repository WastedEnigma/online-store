package oleg.tokarenko.onlinestore.controller;

import oleg.tokarenko.onlinestore.dto.request.ProductCriteria;
import oleg.tokarenko.onlinestore.dto.request.ProductRequest;
import oleg.tokarenko.onlinestore.dto.response.PaginationResponse;
import oleg.tokarenko.onlinestore.dto.response.product.ProductResponse;
import oleg.tokarenko.onlinestore.dto.response.product.ProductWithSubCategoryAndModelResponse;
import oleg.tokarenko.onlinestore.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/product")
@CrossOrigin
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping
    public void save(@RequestBody @Valid ProductRequest request) throws IOException {
        productService.save(request);
    }

    @PutMapping
    public void update(Long id, @RequestBody @Valid ProductRequest request) throws IOException {
        productService.update(request, id);
    }

    @DeleteMapping
    public void delete(Long id) {
        productService.delete(id);
    }

    @GetMapping
    public List<ProductResponse> findAll() {
        return productService.findAll();
    }

    @GetMapping("/filter")
    public PaginationResponse<ProductResponse> findAll(@Valid ProductCriteria criteria) {
        return productService.findAll(criteria);
    }

    @GetMapping("/byId")
    public ProductResponse findOne(Long id) {
        return new ProductResponse(productService.findOne(id));
    }

    @GetMapping("/{id}")
    public ProductWithSubCategoryAndModelResponse findOneWithSubCategoryAndModel(@PathVariable Long id) {
        return productService.findOneWithSubCategoryAndModel(id);
    }
}
