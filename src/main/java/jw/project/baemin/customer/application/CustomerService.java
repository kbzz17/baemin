package jw.project.baemin.customer.application;

import jw.project.baemin.common.ApiResponse;
import jw.project.baemin.customer.domain.Customer;
import jw.project.baemin.customer.infrastructure.CustomerRepository;
import jw.project.baemin.customer.presentation.request.CreateCustomerRequest;
import jw.project.baemin.customer.presentation.request.UpdateCustomerRequest;
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

    public CustomerResponse getCustomer(Long customerId) {
        Customer customer = customerRepository.findById(customerId)
            .orElseThrow(RuntimeException::new);
        return CustomerResponse.from(customer);
    }

    public CustomerResponse updateCustomer(Long customerId, UpdateCustomerRequest request) {

        Customer customer = customerRepository.findById(customerId)
            .orElseThrow(RuntimeException::new);
        customer.update(request);
        Customer updateCustomer = customerRepository.save(customer);

        return CustomerResponse.from(updateCustomer);
    }

    public void deleteCustomer(Long customerId) {
        customerRepository.deleteById(customerId);
    }
}
