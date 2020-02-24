package oleg.tokarenko.onlinestore.dto.request;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.*;

import javax.validation.constraints.*;

@Getter
@Setter
public class PaginationRequest {

    @Positive
    @NotNull
    private Integer size;

    @PositiveOrZero
    @NotNull
    private Integer page;

    @NotNull
    private Sort.Direction direction;

    @NotEmpty
    private String[] fields;

    public Pageable toPageable() {
        return PageRequest.of(page, size, direction, fields);
    }
}
