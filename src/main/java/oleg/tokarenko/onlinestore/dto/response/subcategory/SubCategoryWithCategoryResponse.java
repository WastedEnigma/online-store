package oleg.tokarenko.onlinestore.dto.response.subcategory;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import oleg.tokarenko.onlinestore.dto.response.CategoryResponse;
import oleg.tokarenko.onlinestore.entity.SubCategory;

@Getter
@Setter
public class SubCategoryWithCategoryResponse {

    private Long id;
    private String name;

    @JsonProperty("category")
    private CategoryResponse categoryResponse;

    public SubCategoryWithCategoryResponse(SubCategory subCategory) {
        id = subCategory.getId();
        name = subCategory.getName();

        if (subCategory.getCategory() != null) {
            categoryResponse = new CategoryResponse(subCategory.getCategory());
        }
    }
}
