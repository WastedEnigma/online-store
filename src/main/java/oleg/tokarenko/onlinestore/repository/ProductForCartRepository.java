package oleg.tokarenko.onlinestore.repository;

import oleg.tokarenko.onlinestore.entity.ProductForCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductForCartRepository extends JpaRepository<ProductForCart, Long> {
}
