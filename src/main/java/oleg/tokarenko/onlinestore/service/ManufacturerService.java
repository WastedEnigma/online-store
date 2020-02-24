package oleg.tokarenko.onlinestore.service;

import oleg.tokarenko.onlinestore.dto.request.ManufacturerRequest;
import oleg.tokarenko.onlinestore.dto.response.manufacturer.ManufacturerResponse;
import oleg.tokarenko.onlinestore.dto.response.manufacturer.ManufacturerWithCountryResponse;
import oleg.tokarenko.onlinestore.entity.Manufacturer;
import oleg.tokarenko.onlinestore.repository.ManufacturerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ManufacturerService {

    @Autowired
    private ManufacturerRepository manufacturerRepository;

    @Autowired
    private CountryService countryService;

    public void save(ManufacturerRequest request) {
        Manufacturer manufacturer = manufacturerRequestToManufacturer(request, null);
        manufacturerRepository.save(manufacturer);
    }

    public void update(ManufacturerRequest request, Long id) {
        Manufacturer manufacturer = manufacturerRequestToManufacturer(request, findOne(id));
        manufacturerRepository.save(manufacturer);
    }

    public ManufacturerWithCountryResponse findOneWithCountry(Long id) {
        return new ManufacturerWithCountryResponse(findOne(id));
    }

    public List<ManufacturerResponse> findAll() {
        return manufacturerRepository.findAll()
                .stream()
                .map(ManufacturerResponse::new)
                .collect(Collectors.toList());
    }

    public Manufacturer findOne(Long id) {
        return manufacturerRepository
                .findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Manufacturer with id " + id + " doesn't exist."));
    }

    public void delete(Long id) {
        manufacturerRepository.delete(findOne(id));
    }

    private Manufacturer manufacturerRequestToManufacturer(ManufacturerRequest request, Manufacturer manufacturer) {
        if (manufacturer == null) {
            manufacturer = new Manufacturer();
        }

        manufacturer.setName(request.getName());
        manufacturer.setCountry(countryService.findOne(request.getCountryId()));

        return manufacturer;
    }
}
