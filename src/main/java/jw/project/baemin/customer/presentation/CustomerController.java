package jw.project.baemin.customer.presentation;

import jw.project.baemin.common.ApiResponse;
import jw.project.baemin.customer.application.CustomerService;
import jw.project.baemin.customer.presentation.request.CreateCustomerRequest;
import jw.project.baemin.customer.presentation.request.UpdateCustomerRequest;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/customer")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    public ApiResponse<?> createCustomer(@RequestBody CreateCustomerRequest request) {
        return ApiResponse.success(customerService.createCustomer(request));
    }

    @GetMapping("/{customerId}")
    public ApiResponse<?> getCustomer(@PathVariable Long customerId) {
        return ApiResponse.success(customerService.getCustomer(customerId));
    }

    @PutMapping("/{customerId}")
    public ApiResponse<?> updateCustomer(@PathVariable Long customerId,
        @RequestBody UpdateCustomerRequest request) {
        return ApiResponse.success(customerService.updateCustomer(customerId, request));
    }

    @DeleteMapping("/{customerId}")
    public ApiResponse<?> deleteCustomer(@PathVariable Long customerId) {
        customerService.deleteCustomer(customerId);
        return ApiResponse.success(null);
    }
}
