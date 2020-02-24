package oleg.tokarenko.onlinestore.dto.response;

import lombok.Getter;
import lombok.Setter;
import oleg.tokarenko.onlinestore.entity.Country;
import oleg.tokarenko.onlinestore.entity.Manufacturer;

import java.util.Set;

@Getter
@Setter
public class CountryResponse {

    private Long id;
    private String name;

    public CountryResponse(Country country) {
        id = country.getId();
        name = country.getName();
    }
}
