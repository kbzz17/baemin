package jw.project.baemin.customer.presentation;

import java.util.List;
import jw.project.baemin.common.ApiResponse;
import jw.project.baemin.customer.application.CustomerAddressService;
import jw.project.baemin.customer.presentation.request.CustomerAddress.CreateCustomerAddressRequest;
import jw.project.baemin.customer.presentation.request.UpdateCustomerAddressRequest;
import jw.project.baemin.customer.presentation.response.CustomerAddress.CustomerAddressResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/customerAddress/")
public class CustomerAddressController {

    private final CustomerAddressService customerAddressService;

    @PostMapping("/{customerId}")
    public ApiResponse<?> createCustomerAddress(
        @PathVariable Long customerId,
        @RequestBody CreateCustomerAddressRequest request) {
        return ApiResponse.success(
            customerAddressService.createCustomerAddress(customerId, request));
    }

    @GetMapping("/{customerId}")
    public ApiResponse<?> findCustomerAddress(@PathVariable Long customerId) {
        List<CustomerAddressResponse> response =
            customerAddressService.findCustomerAddresses(customerId);

        return ApiResponse.success(response);
    }

    @PutMapping("/{customerId}")
    public ApiResponse<?> updateCustomerAddress(@PathVariable Long customerId,
        @RequestBody UpdateCustomerAddressRequest request) {
        CustomerAddressResponse response = customerAddressService.updateCustomerAddress(
            customerId, request);
        return ApiResponse.success(response);
    }

    @DeleteMapping("/{customerId}/{addressId}")
    public ApiResponse<?> deleteCustomerAddress(@PathVariable Long customerId,
        @PathVariable Long addressId) {
        customerAddressService.deleteCustomerAddress(customerId,addressId);
        return ApiResponse.success(null);
    }
}
