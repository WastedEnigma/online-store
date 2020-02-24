package oleg.tokarenko.onlinestore.dto.request;

import lombok.*;

import javax.validation.constraints.*;

@Getter
@Setter
public class ManufacturerRequest {

    @NotBlank
    private String name;

    @NotNull
    private Long countryId;
}
