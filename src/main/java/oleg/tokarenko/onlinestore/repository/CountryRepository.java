package oleg.tokarenko.onlinestore.repository;

import oleg.tokarenko.onlinestore.entity.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryRepository extends JpaRepository<Country, Long> {
}
