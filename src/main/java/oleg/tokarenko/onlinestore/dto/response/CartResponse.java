package oleg.tokarenko.onlinestore.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import oleg.tokarenko.onlinestore.entity.Cart;

import java.util.Set;
import java.util.stream.Collectors;

@Setter
@Getter

public class CartResponse {

    @JsonProperty("productForCarts")
    private Set<ProductForCartResponse> productForCartResponses;

    public CartResponse(Cart cart) {
        if (cart != null) {
            productForCartResponses = cart.getProductForCarts().stream()
                    .map(ProductForCartResponse::new)
                    .collect(Collectors.toSet());
        }
    }
}
