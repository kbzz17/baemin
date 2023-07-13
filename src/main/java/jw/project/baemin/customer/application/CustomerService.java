package jw.project.baemin.customer.application;

import jw.project.baemin.customer.domain.Customer;
import jw.project.baemin.customer.infrastructure.CustomerRepository;
import jw.project.baemin.customer.presentation.request.CreateCustomerRequest;
import jw.project.baemin.customer.presentation.response.CustomerResponse;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public CustomerResponse createCustomer(CreateCustomerRequest request) {
        Customer customer = request.toEntity();
        return CustomerResponse.from(customerRepository.save(customer));
    }
}
