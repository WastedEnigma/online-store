package oleg.tokarenko.onlinestore.controller;

import oleg.tokarenko.onlinestore.dto.request.CategoryRequest;
import oleg.tokarenko.onlinestore.dto.response.CategoryResponse;
import oleg.tokarenko.onlinestore.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Set;

@RestController
@RequestMapping("/category")
@CrossOrigin
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping
    public void save(@RequestBody @Valid CategoryRequest request) {
        categoryService.save(request);
    }

    @PutMapping
    public void update(Long id, @RequestBody @Valid CategoryRequest request) {
        categoryService.update(request, id);
    }

    @DeleteMapping
    public void delete(Long id) {
        categoryService.delete(id);
    }

    @GetMapping
    public Set<CategoryResponse> findAll() {
        return categoryService.findAll();
    }
}
