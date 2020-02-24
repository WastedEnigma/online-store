package oleg.tokarenko.onlinestore.dto.request;

import lombok.*;

import javax.validation.constraints.*;

@Getter
@Setter
public class ProductForOrderRequest {

    @Positive
    private Long count;

    @NotNull
    private Long productId;

    @NotNull
    private Long orderId;
}
