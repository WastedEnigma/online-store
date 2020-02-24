package oleg.tokarenko.onlinestore.controller;

import oleg.tokarenko.onlinestore.dto.request.ModelRequest;
import oleg.tokarenko.onlinestore.dto.response.model.ModelResponse;
import oleg.tokarenko.onlinestore.dto.response.model.ModelWithManufacturerResponse;
import oleg.tokarenko.onlinestore.service.ModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

@RestController
@RequestMapping("/model")
@CrossOrigin
public class ModelController {

    @Autowired
    private ModelService modelService;

    @PostMapping
    public void save(@RequestBody @Valid ModelRequest request) {
        modelService.save(request);
    }

    @PutMapping
    public void update(Long id, @RequestBody @Valid ModelRequest request) {
        modelService.update(request, id);
    }

    @DeleteMapping
    public void delete(Long id) {
        modelService.delete(id);
    }

    @GetMapping
    public Set<ModelResponse> findAll() {
        return modelService.findAll();
    }

    @GetMapping("{id}")
    public ModelWithManufacturerResponse findOne(@PathVariable Long id) {
        return modelService.findOneWithManufacturer(id);
    }
}
