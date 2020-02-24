package oleg.tokarenko.onlinestore.service;

import oleg.tokarenko.onlinestore.dto.request.CountryRequest;
import oleg.tokarenko.onlinestore.dto.response.CountryResponse;
import oleg.tokarenko.onlinestore.entity.Country;
import oleg.tokarenko.onlinestore.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CountryService {

    @Autowired
    private CountryRepository countryRepository;

    public void save(CountryRequest request) {
        Country country = countryRequestToCountry(request, null);
        countryRepository.save(country);
    }

    public void update(CountryRequest request, Long id) {
        Country country = countryRequestToCountry(request, findOne(id));
        countryRepository.save(country);
    }

    public void delete(Long id) {
        countryRepository.delete(findOne(id));
    }

    public List<CountryResponse> findAll() {
        return countryRepository
                .findAll()
                .stream()
                .map(CountryResponse::new)
                .collect(Collectors.toList());
    }

    public CountryResponse findOneById(Long id) {
        return new CountryResponse(findOne(id));
    }

    public Country findOne(Long id) {
        return countryRepository
                .findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Country with id " + id + " doesn't exist."));
    }

    private Country countryRequestToCountry(CountryRequest request, Country country) {
        if (country == null) {
            country = new Country();
        }

        country.setName(request.getName());

        return country;
    }
}
