package oleg.tokarenko.onlinestore.dto.response;

import lombok.*;
import oleg.tokarenko.onlinestore.dto.response.subcategory.SubCategoryResponse;
import oleg.tokarenko.onlinestore.entity.*;

import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
public class CategoryResponse {

    private Long id;
    private String name;
    private Set<SubCategoryResponse> subCategories;

    public CategoryResponse(Category category) {
        id = category.getId();
        name = category.getName();
        subCategories = category.getSubCategories()
            .stream()
            .map(SubCategoryResponse::new)
            .collect(Collectors.toSet());
    }
}
