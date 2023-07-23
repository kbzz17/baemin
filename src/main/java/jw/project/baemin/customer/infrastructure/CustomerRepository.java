package jw.project.baemin.customer.infrastructure;

import java.util.Optional;
import jw.project.baemin.customer.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
