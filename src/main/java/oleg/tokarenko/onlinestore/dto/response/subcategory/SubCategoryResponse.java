package oleg.tokarenko.onlinestore.dto.response.subcategory;

import lombok.*;
import oleg.tokarenko.onlinestore.dto.response.product.ProductResponse;
import oleg.tokarenko.onlinestore.entity.*;

import java.util.*;
import java.util.stream.Collectors;

@Getter
@Setter
public class SubCategoryResponse {

    private Long id;
    private String name;
    private String categoryName;
    private Set<ProductResponse> products;

    public SubCategoryResponse(SubCategory subCategory) {
        id = subCategory.getId();
        name = subCategory.getName();

        if (subCategory.getCategory() != null) {
            categoryName = subCategory.getCategory().getName();
        }

        products = subCategory.getProducts()
                .stream()
                .map(ProductResponse::new)
                .collect(Collectors.toSet());
    }
}
