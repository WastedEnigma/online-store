package oleg.tokarenko.onlinestore.service;

import oleg.tokarenko.onlinestore.dto.request.ProductCriteria;
import oleg.tokarenko.onlinestore.dto.request.ProductRequest;
import oleg.tokarenko.onlinestore.dto.response.PaginationResponse;
import oleg.tokarenko.onlinestore.dto.response.product.ProductResponse;
import oleg.tokarenko.onlinestore.dto.response.product.ProductWithSubCategoryAndModelResponse;
import oleg.tokarenko.onlinestore.entity.Product;
import oleg.tokarenko.onlinestore.entity.User;
import oleg.tokarenko.onlinestore.repository.ProductRepository;
import oleg.tokarenko.onlinestore.specification.ProductSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ModelService modelService;

    @Autowired
    private SubCategoryService subCategoryService;

    @Autowired
    private FileService fileService;

    public void save(ProductRequest request) throws IOException {
        Product product = productRequestToProduct(request, null);
        productRepository.save(product);
    }

    public void update(ProductRequest request, Long id) throws IOException {
        Product product = productRequestToProduct(request, findOne(id));
        productRepository.save(product);
    }

    public void delete(Long id) {
        productRepository.delete(findOne(id));
    }

    public ProductWithSubCategoryAndModelResponse findOneWithSubCategoryAndModel(Long id) {
        return new ProductWithSubCategoryAndModelResponse(findOne(id));
    }

    public List<ProductResponse> findAll() {
        return productRepository.findAll()
                .stream()
                .map(ProductResponse::new)
                .collect(Collectors.toList());
    }

    public Product findOne(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Product with id " + id + " doesn't exist."));
    }

    public PaginationResponse<ProductResponse> findAll(ProductCriteria criteria) {
        Page<Product> page = productRepository.findAll(
                new ProductSpecification(criteria),
                criteria.getPaginationRequest().toPageable());

        return new PaginationResponse<>(page.getTotalPages(),
                page.getTotalElements(),
                page.get()
                        .map(ProductResponse::new)
                        .collect(Collectors.toList()));
    }

    public Set<ProductResponse> findAllByUsername() {
        final String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return productRepository.findAllByUsername(username)
                .stream()
                .map(ProductResponse::new)
                .collect(Collectors.toSet());
    }

    public void addToCart(Long id) {
        findAllByUsername().add(new ProductResponse(findOne(id)));
        /*Set<ProductResponse> products = findAllByUsername();
        products.add(new ProductResponse(findOne(id)));
        final User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        user.setProducts(products.stream().map());*/
    }

    private Product productRequestToProduct(ProductRequest request, Product product) throws IOException {
        if (product == null) {
            product = new Product();
        }

        product.setName(request.getName());
        product.setPrice(request.getPrice());
        product.setRating(request.getRating());
        product.setDescription(request.getDescription());
        product.setAvailable(request.getAvailable());
        product.setModel(modelService.findOne(request.getModelId()));
        product.setSubCategory(subCategoryService.findOne(request.getSubCategoryId()));

        if (request.getData() != null
                && !request.getData().isEmpty()) {
            String path = fileService.saveFile(request.getData(), request.getFileName());
            product.setImage(path);
        }

        return product;
    }
}
