package oleg.tokarenko.onlinestore.specification;

import oleg.tokarenko.onlinestore.dto.request.ProductCriteria;
import oleg.tokarenko.onlinestore.entity.Product;
import oleg.tokarenko.onlinestore.entity.SubCategory;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

public class ProductSpecification implements Specification<Product> {

    private String name;
    private Double minPrice;
    private Double maxPrice;
    private Long subCategoryId;

    public ProductSpecification(ProductCriteria criteria) {
        name = criteria.getName();
        minPrice = criteria.getMinPrice();
        maxPrice = criteria.getMaxPrice();
        subCategoryId = criteria.getSubCategoryId();
    }

    @Override
    public Predicate toPredicate(
            Root<Product> root,
            CriteriaQuery<?> criteriaQuery,
            CriteriaBuilder criteriaBuilder) {
        final List<Predicate> predicates = new ArrayList<>();
        predicates.add(findByNameLike(root, criteriaBuilder));
        predicates.add(findByPrice(root, criteriaBuilder));
        predicates.add(findBySubCategory(root, criteriaBuilder));

        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }

    private Predicate findByNameLike(Root<Product> root, CriteriaBuilder criteriaBuilder) {
        Predicate predicate;

        if (name != null) {
            predicate = criteriaBuilder.like(root.get("name"), '%' + name + '%');
        } else {
            predicate = criteriaBuilder.conjunction();
        }

        return predicate;
    }

    private Predicate findByPrice(Root<Product> root, CriteriaBuilder criteriaBuilder) {
        Predicate predicate;

        if (minPrice != null && maxPrice != null) {
            predicate = criteriaBuilder.between(root.get("price"), minPrice, maxPrice);
        } else if (minPrice != null) {
            predicate = criteriaBuilder.greaterThanOrEqualTo(root.get("price"), minPrice);
        } else if (maxPrice != null) {
            predicate = criteriaBuilder.lessThanOrEqualTo(root.get("price"), maxPrice);
        } else {
            predicate = criteriaBuilder.conjunction();
        }

        return predicate;
    }

    private Predicate findBySubCategory(Root<Product> root, CriteriaBuilder criteriaBuilder) {
        Predicate predicate;

        if (subCategoryId != null) {
            final Join<Product, SubCategory> subCategory = root.join("subCategory");
            predicate = criteriaBuilder.equal(subCategory.get("id"), subCategoryId);
        } else {
            predicate = criteriaBuilder.conjunction();
        }

        return predicate;
    }
}
