package oleg.tokarenko.onlinestore.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductCriteria {

    private String name;
    private Double minPrice;
    private Double maxPrice;
    private Long subCategoryId;

    private PaginationRequest paginationRequest;
}
