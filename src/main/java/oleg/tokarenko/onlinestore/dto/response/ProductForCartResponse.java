package oleg.tokarenko.onlinestore.dto.response;

import lombok.Getter;
import lombok.Setter;
import oleg.tokarenko.onlinestore.entity.ProductForCart;

@Getter
@Setter

public class ProductForCartResponse {

    private Long id;
    private String productName;
    private String productImage;
    private Integer count;

    public ProductForCartResponse(ProductForCart productForCart) {
        id = productForCart.getId();

        if (productForCart.getProduct() != null) {
            productName = productForCart.getProduct().getName();
            productImage = productForCart.getProduct().getImage();
        }

        count = productForCart.getCount();
    }
}
