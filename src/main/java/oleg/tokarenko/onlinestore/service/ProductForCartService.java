package oleg.tokarenko.onlinestore.service;

import oleg.tokarenko.onlinestore.entity.ProductForCart;
import oleg.tokarenko.onlinestore.repository.ProductForCartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductForCartService {

    @Autowired
    private ProductForCartRepository productForCartRepository;

    public void delete(Long id) {
        productForCartRepository.delete(findOne(id));
    }

    public ProductForCart findOne(Long id) {
        return productForCartRepository
                .findById(id)
                .orElseThrow(() -> new IllegalArgumentException("ProductForCart with id " + id + " doesn't exist."));
    }
}
