package jw.project.baemin.customer.infrastructure;

import java.util.List;
import java.util.Optional;
import jw.project.baemin.customer.domain.CustomerAddress;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerAddressRepository extends JpaRepository<CustomerAddress, Long> {

    List<CustomerAddress> findByCustomerId(Long customerId);
}
