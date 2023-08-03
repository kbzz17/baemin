package jw.project.baemin.customer.application;

import java.util.List;
import java.util.stream.Collectors;
import jw.project.baemin.customer.domain.Customer;
import jw.project.baemin.customer.domain.CustomerAddress;
import jw.project.baemin.customer.infrastructure.CustomerAddressRepository;
import jw.project.baemin.customer.infrastructure.CustomerRepository;
import jw.project.baemin.customer.presentation.request.CustomerAddress.CreateCustomerAddressRequest;
import jw.project.baemin.customer.presentation.request.UpdateCustomerAddressRequest;
import jw.project.baemin.customer.presentation.response.CustomerAddress.CustomerAddressResponse;
import jw.project.baemin.region.application.RegionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerAddressService {

    private final RegionService regionService;
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

    public List<CustomerAddressResponse> findCustomerAddresses(Long customerId) {
        Customer customer = validateFindByCustomerId(customerId);
        return customer.getAddresses()
            .stream()
            .map(CustomerAddressResponse::from)
            .collect(Collectors.toList());
    }

    public CustomerAddressResponse findCustomerAddressByIsMain(Long customerId) {
        return CustomerAddressResponse.from(
            customerAddressRepository.findByCustomerIdAndIsMainIsTrue(customerId));
    }

    public CustomerAddressResponse updateCustomerAddress(Long id,
        UpdateCustomerAddressRequest request) {
        CustomerAddress customerAddress = validateFindByAddressId(id);

        Long updateRegionId = regionService.findByRegionAddress(request.regionAddress()).getId();

        customerAddress.update(
            request.addressName(),
            request.regionAddress(),
            request.detailAddress(),
            request.isMainAddress(),
            updateRegionId);

        CustomerAddress update = customerAddressRepository.save(customerAddress);

        return CustomerAddressResponse.from(update);
    }

    public void deleteCustomerAddress(Long customerId, Long addressId) {
        Customer customer = validateFindByCustomerId(customerId);
        CustomerAddress customerAddress = validateFindByAddressId(addressId);
        customer.removeAddress(customerAddress);
    }

    private Customer validateFindByCustomerId(Long customerId) {
        return customerRepository.findById(customerId).orElseThrow(RuntimeException::new);
    }

    private CustomerAddress validateFindByAddressId(Long addressId) {
        return customerAddressRepository.findById(addressId).orElseThrow(RuntimeException::new);
    }

}
