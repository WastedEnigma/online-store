package oleg.tokarenko.onlinestore.dto.response;

import lombok.*;

import java.util.List;

@Getter
@AllArgsConstructor
public class PaginationResponse<T> {

    private Integer totalPages;
    private Long totalElements;
    private List<T> data;
}
