package oleg.tokarenko.onlinestore.service;

import oleg.tokarenko.onlinestore.dto.request.ProductForOrderRequest;
import oleg.tokarenko.onlinestore.dto.response.ProductForOrderResponse;
import oleg.tokarenko.onlinestore.entity.ProductForOrder;
import oleg.tokarenko.onlinestore.repository.ProductForOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductForOrderService {

    @Autowired
    private ProductForOrderRepository productForOrderRepository;

    @Autowired
    private ProductService productService;

    @Autowired
    private _OrderService orderService;

    public void save(ProductForOrderRequest request) {
        ProductForOrder productForOrder = productForOrderRequestToProductForOrder(request, null);
        productForOrderRepository.save(productForOrder);
    }

    public void update(ProductForOrderRequest request, Long id) {
        ProductForOrder productForOrder = productForOrderRequestToProductForOrder(request, findOne(id));
        productForOrderRepository.save(productForOrder);
    }

    public void delete(Long id) {
        productForOrderRepository.delete(findOne(id));
    }

    public List<ProductForOrderResponse> findAll() {
        return productForOrderRepository.findAll()
                .stream()
                .map(ProductForOrderResponse::new)
                .collect(Collectors.toList());
    }

    public ProductForOrder findOne(Long id) {
        return productForOrderRepository
                .findById(id)
                .orElseThrow(() -> new IllegalArgumentException("ProductForOrder with id " + id + " doesn't exist."));
    }

    private ProductForOrder productForOrderRequestToProductForOrder(ProductForOrderRequest request, ProductForOrder productForOrder) {
        if (productForOrder == null) {
            productForOrder = new ProductForOrder();
        }

        productForOrder.setCount(request.getCount());
        productForOrder.setProduct(productService.findOne(request.getProductId()));
        productForOrder.setOrder(orderService.findOne(request.getOrderId()));

        return productForOrder;
    }
}
