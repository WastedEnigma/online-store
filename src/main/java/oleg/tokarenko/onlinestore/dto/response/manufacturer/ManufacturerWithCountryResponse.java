package oleg.tokarenko.onlinestore.dto.response.manufacturer;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import oleg.tokarenko.onlinestore.dto.response.CountryResponse;
import oleg.tokarenko.onlinestore.entity.Manufacturer;

@Getter
@Setter
public class ManufacturerWithCountryResponse {

    private Long id;
    private String name;

    @JsonProperty("country")
    private CountryResponse countryResponse;

    public ManufacturerWithCountryResponse(Manufacturer manufacturer) {
        id = manufacturer.getId();
        name = manufacturer.getName();

        if (manufacturer.getCountry() != null) {
            countryResponse = new CountryResponse(manufacturer.getCountry());
        }
    }
}
