package oleg.tokarenko.onlinestore.dto.response.product;

import lombok.*;
import oleg.tokarenko.onlinestore.entity.Product;

import java.util.Set;

@Getter
@Setter
public class ProductResponse {

    private Long id;
    private String name;
    private Double price;
    private Double rating;
    private String description;
    private Boolean available;
    private String modelName;
    private String subCategoryName;
    private String image;

    public ProductResponse(Product product) {
        id = product.getId();
        name = product.getName();
        price = product.getPrice();
        rating = product.getRating();
        description = product.getDescription();
        available = product.getAvailable();
        image = product.getImage();

        if (product.getModel() != null) {
            modelName = product.getModel().getName();
        }

        if (product.getSubCategory() != null) {
            subCategoryName = product.getSubCategory().getName();
        }
    }
}
