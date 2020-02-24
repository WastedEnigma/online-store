package oleg.tokarenko.onlinestore.controller;

import oleg.tokarenko.onlinestore.dto.request.CountryRequest;
import oleg.tokarenko.onlinestore.dto.response.CountryResponse;
import oleg.tokarenko.onlinestore.entity.Country;
import oleg.tokarenko.onlinestore.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/country")
@CrossOrigin
public class CountryController {

    @Autowired
    private CountryService countryService;

    @PostMapping
    public void save(@RequestBody @Valid CountryRequest request) {
        countryService.save(request);
    }

    @PutMapping
    public void update(Long id, @RequestBody @Valid CountryRequest request) {
        countryService.update(request, id);
    }

    @DeleteMapping
    public void delete(Long id) {
        countryService.delete(id);
    }

    @GetMapping
    public List<CountryResponse> findAll() {
        return countryService.findAll();
    }

    @GetMapping("/{id}")
    public CountryResponse findOneById(@PathVariable Long id) {
        return countryService.findOneById(id);
    }
}
