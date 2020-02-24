package oleg.tokarenko.onlinestore.dto.request;

import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class CountryRequest {

    @NotBlank
    private String name;
}
