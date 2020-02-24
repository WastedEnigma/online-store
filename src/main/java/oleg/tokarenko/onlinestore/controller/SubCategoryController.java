package oleg.tokarenko.onlinestore.controller;

import oleg.tokarenko.onlinestore.dto.request.SubCategoryRequest;
import oleg.tokarenko.onlinestore.dto.response.subcategory.SubCategoryResponse;
import oleg.tokarenko.onlinestore.dto.response.subcategory.SubCategoryWithCategoryResponse;
import oleg.tokarenko.onlinestore.service.SubCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Set;

@RestController
@RequestMapping("/sub_category")
@CrossOrigin
public class SubCategoryController {

    @Autowired
    private SubCategoryService subCategoryService;

    @PostMapping
    public void save(@RequestBody @Valid SubCategoryRequest request) {
        subCategoryService.save(request);
    }

    @PutMapping
    public void update(Long id, @RequestBody @Valid SubCategoryRequest request) {
        subCategoryService.update(request, id);
    }

    @DeleteMapping
    public void delete(Long id) {
        subCategoryService.delete(id);
    }

    @GetMapping
    public Set<SubCategoryResponse> findAll() {
        return subCategoryService.findAll();
    }

    @GetMapping("{id}")
    public SubCategoryWithCategoryResponse findOne(@PathVariable Long id) {
        return subCategoryService.findOneWithCategory(id);
    }
}
