package oleg.tokarenko.onlinestore.service;

import oleg.tokarenko.onlinestore.dto.request._OrderRequest;
import oleg.tokarenko.onlinestore.dto.response._OrderResponse;
import oleg.tokarenko.onlinestore.entity._Order;
import oleg.tokarenko.onlinestore.repository._OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class _OrderService {

    @Autowired
    private _OrderRepository orderRepository;

    public void save(_OrderRequest request) {
        _Order order = orderRequestToOrder(request, null);
        orderRepository.save(order);
    }

    public void update(_OrderRequest request, Long id) {
        _Order order = orderRequestToOrder(request, findOne(id));
        orderRepository.save(order);
    }

    public void delete(Long id) {
        orderRepository.delete(findOne(id));
    }

    public List<_OrderResponse> findAll() {
        return orderRepository.findAll()
                .stream()
                .map(_OrderResponse::new)
                .collect(Collectors.toList());
    }

    public _Order findOne(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Order with id " + id + " doesn't exist."));
    }

    private _Order orderRequestToOrder(_OrderRequest request, _Order order) {
        if (order == null) {
            order = new _Order();
        }

        order.setCreationDate(request.getCreationDate());

        return order;
    }
}
