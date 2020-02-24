package oleg.tokarenko.onlinestore.dto.response.model;

import lombok.Getter;
import lombok.Setter;
import oleg.tokarenko.onlinestore.entity.Model;

@Getter
@Setter
public class ModelResponse {

    private Long id;
    private String name;
    private String manufacturerName;

    public ModelResponse(Model model) {
        id = model.getId();
        name = model.getName();

        if (model.getManufacturer() != null) {
            manufacturerName = model.getManufacturer().getName();
        }
    }
}
