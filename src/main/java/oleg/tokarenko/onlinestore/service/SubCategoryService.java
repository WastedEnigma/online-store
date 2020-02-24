package oleg.tokarenko.onlinestore.service;

import oleg.tokarenko.onlinestore.dto.request.SubCategoryRequest;
import oleg.tokarenko.onlinestore.dto.response.subcategory.SubCategoryResponse;
import oleg.tokarenko.onlinestore.dto.response.subcategory.SubCategoryWithCategoryResponse;
import oleg.tokarenko.onlinestore.entity.SubCategory;
import oleg.tokarenko.onlinestore.repository.SubCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class SubCategoryService {

    @Autowired
    private SubCategoryRepository subCategoryRepository;

    @Autowired
    private CategoryService categoryService;

    public void save(SubCategoryRequest request) {
        SubCategory subCategory = subCategoryRequestToSubCategory(request, null);
        subCategoryRepository.save(subCategory);
    }

    public void update(SubCategoryRequest request, Long id) {
        SubCategory subCategory = subCategoryRequestToSubCategory(request, findOne(id));
        subCategoryRepository.save(subCategory);
    }

    public void delete(Long id) {
        subCategoryRepository.delete(findOne(id));
    }

    public SubCategoryWithCategoryResponse findOneWithCategory(Long id) {
        return new SubCategoryWithCategoryResponse(findOne(id));
    }

    public Set<SubCategoryResponse> findAll() {
        return subCategoryRepository.findAll()
                .stream()
                .map(SubCategoryResponse::new)
                .collect(Collectors.toSet());
    }

    public SubCategory findOne(Long id) {
        return subCategoryRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("SubCategory with id " + id + " doesn't exist."));
    }

    private SubCategory subCategoryRequestToSubCategory(SubCategoryRequest request, SubCategory subCategory) {
        if (subCategory == null) {
            subCategory = new SubCategory();
        }

        subCategory.setName(request.getName());
        subCategory.setCategory(categoryService.findOne(request.getCategoryId()));

        return subCategory;
    }
}
