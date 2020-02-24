package oleg.tokarenko.onlinestore.controller;

import oleg.tokarenko.onlinestore.dto.request._OrderRequest;
import oleg.tokarenko.onlinestore.dto.response._OrderResponse;
import oleg.tokarenko.onlinestore.service._OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/_order")
@CrossOrigin
public class _OrderController {

    @Autowired
    private _OrderService orderService;

    @PostMapping
    public void save(@RequestBody @Valid _OrderRequest request) {
        orderService.save(request);
    }

    @PutMapping
    public void update(Long id, @RequestBody @Valid _OrderRequest request) {
        orderService.update(request, id);
    }

    @DeleteMapping
    public void delete(Long id) {
        orderService.delete(id);
    }

    @GetMapping
    public List<_OrderResponse> findAll() {
        return orderService.findAll();
    }
}
