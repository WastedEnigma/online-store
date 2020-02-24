package oleg.tokarenko.onlinestore.service;

import oleg.tokarenko.onlinestore.dto.request.CategoryRequest;
import oleg.tokarenko.onlinestore.dto.response.CategoryResponse;
import oleg.tokarenko.onlinestore.entity.Category;
import oleg.tokarenko.onlinestore.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public void save(CategoryRequest request) {
        Category category = categoryRequestToCategory(request, null);
        categoryRepository.save(category);
    }

    public void update(CategoryRequest request, Long id) {
        Category category = categoryRequestToCategory(request, findOne(id));
        categoryRepository.save(category);
    }

    public void delete(Long id) {
        categoryRepository.delete(findOne(id));
    }

    public Set<CategoryResponse> findAll() {
        return categoryRepository.findAll()
                .stream()
                .map(CategoryResponse::new)
                .collect(Collectors.toSet());
    }

    public Category findOne(Long id) {
        return categoryRepository
                .findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Category with id " + id + " doesn't exist."));
    }

    private Category categoryRequestToCategory(CategoryRequest request, Category category) {
        if (category == null) {
            category = new Category();
        }

        category.setName(request.getName());

        return category;
    }
}
