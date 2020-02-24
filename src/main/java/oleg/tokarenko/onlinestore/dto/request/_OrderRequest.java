package oleg.tokarenko.onlinestore.dto.request;

import lombok.*;

import javax.validation.constraints.*;
import java.time.LocalDate;

@Getter
@Setter
public class _OrderRequest {

    @NotNull
    private LocalDate creationDate;
}
