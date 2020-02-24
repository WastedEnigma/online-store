package oleg.tokarenko.onlinestore.repository;

import oleg.tokarenko.onlinestore.entity._Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface _OrderRepository extends JpaRepository<_Order, Long> {
}
