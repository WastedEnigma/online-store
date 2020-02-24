package oleg.tokarenko.onlinestore.dto.request;

import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class CategoryRequest {

    @NotBlank
    private String name;
}
