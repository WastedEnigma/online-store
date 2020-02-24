package oleg.tokarenko.onlinestore.dto.response;

import lombok.*;
import oleg.tokarenko.onlinestore.entity.ProductForOrder;

@Getter
@Setter
public class ProductForOrderResponse {

    private Long id;
    private Long count;
    private String productName;
    private String orderDate;

    public ProductForOrderResponse(ProductForOrder productForOrder) {
        id = productForOrder.getId();
        count = productForOrder.getCount();

        if (productForOrder.getProduct() != null) {
            productName = productForOrder.getProduct().getName();
        }

        if (productForOrder.getOrder() != null) {
            orderDate = productForOrder.getOrder()
                    .getCreationDate()
                    .toString();
        }
    }
}
