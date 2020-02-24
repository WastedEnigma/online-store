package oleg.tokarenko.onlinestore.dto.response.manufacturer;

import lombok.Getter;
import lombok.Setter;
import oleg.tokarenko.onlinestore.entity.Manufacturer;

@Getter
@Setter
public class ManufacturerResponse {

    private Long id;
    private String name;
    private String countryName;

    public ManufacturerResponse(Manufacturer manufacturer) {
        id = manufacturer.getId();
        name = manufacturer.getName();

        if (manufacturer.getCountry() != null) {
            countryName = manufacturer.getCountry().getName();
        }
    }
}
