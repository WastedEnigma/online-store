package oleg.tokarenko.onlinestore.dto.request;

import lombok.*;

import javax.validation.constraints.*;

@Getter
@Setter
public class ProductRequest {

    @NotBlank
    private String name;

    @Positive
    private Double price;

    private Double rating;

    private String description;

    private Boolean available;

    private String data;

    private String fileName;

    @NotNull
    private Long modelId;

    @NotNull
    private Long subCategoryId;
}
