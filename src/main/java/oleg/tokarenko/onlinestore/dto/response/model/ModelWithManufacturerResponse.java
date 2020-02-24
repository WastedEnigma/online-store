package oleg.tokarenko.onlinestore.dto.response.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import oleg.tokarenko.onlinestore.dto.response.manufacturer.ManufacturerResponse;
import oleg.tokarenko.onlinestore.entity.Model;

@Getter
@Setter
public class ModelWithManufacturerResponse {

    private Long id;
    private String name;

    @JsonProperty("manufacturer")
    private ManufacturerResponse manufacturerResponse;

    public ModelWithManufacturerResponse(Model model) {
        id = model.getId();
        name = model.getName();

        if (model.getManufacturer() != null) {
            manufacturerResponse = new ManufacturerResponse(model.getManufacturer());
        }
    }
}
