package oleg.tokarenko.onlinestore.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

public class ProductForCartRequest {

    private Long productId;
    private Integer count;
}
