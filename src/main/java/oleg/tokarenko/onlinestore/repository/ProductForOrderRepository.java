package oleg.tokarenko.onlinestore.repository;

import oleg.tokarenko.onlinestore.entity.ProductForOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductForOrderRepository extends JpaRepository<ProductForOrder, Long> {
}
