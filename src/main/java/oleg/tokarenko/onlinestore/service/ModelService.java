package oleg.tokarenko.onlinestore.service;

import oleg.tokarenko.onlinestore.dto.request.ModelRequest;
import oleg.tokarenko.onlinestore.dto.response.model.ModelResponse;
import oleg.tokarenko.onlinestore.dto.response.model.ModelWithManufacturerResponse;
import oleg.tokarenko.onlinestore.entity.Model;
import oleg.tokarenko.onlinestore.repository.ModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ModelService {

    @Autowired
    private ModelRepository modelRepository;

    @Autowired
    private ManufacturerService manufacturerService;

    public void save(ModelRequest request) {
        Model model = modelRequestToModel(request, null);
        modelRepository.save(model);
    }

    public void update(ModelRequest request, Long id) {
        Model model = modelRequestToModel(request, findOne(id));
        modelRepository.save(model);
    }

    public void delete(Long id) {
        modelRepository.delete(findOne(id));
    }

    public Set<ModelResponse> findAll() {
        return modelRepository
                .findAll()
                .stream()
                .map(ModelResponse::new)
                .collect(Collectors.toSet());
    }

    public ModelWithManufacturerResponse findOneWithManufacturer(Long id) {
        return new ModelWithManufacturerResponse(findOne(id));
    }

    public Model findOne(Long id) {
        return modelRepository
                .findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Model with id " + id + " doesn't exist."));
    }

    private Model modelRequestToModel(ModelRequest request, Model model) {
        if (model == null) {
            model = new Model();
        }

        model.setName(request.getName());
        model.setManufacturer(manufacturerService.findOne(request.getManufacturerId()));

        return model;
    }
}
