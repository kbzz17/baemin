package jw.project.baemin.customer.infrastructure;

import jw.project.baemin.customer.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
