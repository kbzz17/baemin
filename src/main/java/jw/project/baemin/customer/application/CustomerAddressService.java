package jw.project.baemin.customer.application;

import java.util.List;
import java.util.stream.Collectors;
import jw.project.baemin.customer.domain.Customer;
import jw.project.baemin.customer.domain.CustomerAddress;
import jw.project.baemin.customer.infrastructure.CustomerAddressRepository;
import jw.project.baemin.customer.infrastructure.CustomerRepository;
import jw.project.baemin.customer.presentation.request.CustomerAddress.CreateCustomerAddressRequest;
import jw.project.baemin.customer.presentation.response.CustomerAddress.CustomerAddressResponse;
import jw.project.baemin.region.application.RegionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerAddressService {

    private final RegionService regionService;
    private final CustomerService customerService;
    private final CustomerRepository customerRepository;
    private final CustomerAddressRepository customerAddressRepository;

    public CustomerAddressResponse createCustomerAddress(
        Long customerId,
        CreateCustomerAddressRequest request) {
        Customer customer = customerRepository
            .findById(customerId)
            .orElseThrow(RuntimeException::new);
        Long regionId = regionService.findByRegionAddress(request.regionAddress()).getId();

        CustomerAddress customerAddress = request.toEntity();
        customerAddress.setRegionId(regionId);

        customer.addAddress(customerAddress);
        customerRepository.save(customer);

        return CustomerAddressResponse.from(customerAddress);
    }

    public List<CustomerAddressResponse> searchCustomerAddresses(Long customerId) {
        return customerAddressRepository.findByCustomerId(customerId)
            .stream()
            .map(CustomerAddressResponse::from)
            .collect(Collectors.toList());
    }

}
