package oleg.tokarenko.onlinestore.dto.response.product;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import oleg.tokarenko.onlinestore.dto.response.model.ModelResponse;
import oleg.tokarenko.onlinestore.dto.response.subcategory.SubCategoryResponse;
import oleg.tokarenko.onlinestore.entity.Product;

@Getter
@Setter
public class ProductWithSubCategoryAndModelResponse {

    private Long id;
    private String name;
    private Double price;
    private Double rating;
    private String description;
    private Boolean available;
    private String image;

    @JsonProperty("subCategory")
    private SubCategoryResponse subCategoryResponse;

    @JsonProperty("model")
    private ModelResponse modelResponse;

    public ProductWithSubCategoryAndModelResponse(Product product) {
        id = product.getId();
        name = product.getName();
        price = product.getPrice();
        rating = product.getRating();
        description = product.getDescription();
        available = product.getAvailable();
        image = product.getImage();

        if (product.getSubCategory() != null) {
            subCategoryResponse = new SubCategoryResponse(product.getSubCategory());
        }

        if (product.getModel() != null) {
            modelResponse = new ModelResponse(product.getModel());
        }
    }
}
