package oleg.tokarenko.onlinestore.controller;

import oleg.tokarenko.onlinestore.dto.request.ManufacturerRequest;
import oleg.tokarenko.onlinestore.dto.response.manufacturer.ManufacturerResponse;
import oleg.tokarenko.onlinestore.dto.response.manufacturer.ManufacturerWithCountryResponse;
import oleg.tokarenko.onlinestore.service.ManufacturerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

@RestController
@RequestMapping("/manufacturer")
@CrossOrigin
public class ManufacturerController {

    @Autowired
    private ManufacturerService manufacturerService;

    @PostMapping
    public void save(@RequestBody @Valid ManufacturerRequest request) {
        manufacturerService.save(request);
    }

    @PutMapping
    public void update(Long id, @RequestBody @Valid ManufacturerRequest request) {
        manufacturerService.update(request, id);
    }

    @DeleteMapping
    public void delete(Long id) {
        manufacturerService.delete(id);
    }

    @GetMapping
    public List<ManufacturerResponse> findAll() {
        return manufacturerService.findAll();
    }

    @GetMapping("/{id}")
    public ManufacturerWithCountryResponse findOne(@PathVariable Long id) {
        return manufacturerService.findOneWithCountry(id);
    }
}
