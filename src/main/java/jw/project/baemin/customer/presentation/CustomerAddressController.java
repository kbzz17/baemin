package jw.project.baemin.customer.presentation;

import jw.project.baemin.common.ApiResponse;
import jw.project.baemin.customer.application.CustomerAddressService;
import jw.project.baemin.customer.presentation.request.CustomerAddress.CreateCustomerAddressRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
}
