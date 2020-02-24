package oleg.tokarenko.onlinestore.dto.response;

import lombok.*;
import oleg.tokarenko.onlinestore.entity._Order;

import java.time.LocalDate;

@Getter
@Setter
public class _OrderResponse {

    private Long id;
    private LocalDate creationDate;

    public _OrderResponse(_Order order) {
        id = order.getId();
        creationDate = order.getCreationDate();
    }
}
