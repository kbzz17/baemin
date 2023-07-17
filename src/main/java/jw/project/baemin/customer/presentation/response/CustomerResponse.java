package jw.project.baemin.customer.presentation.response;

import jw.project.baemin.customer.domain.Customer;

public record CustomerResponse(Long userId, String email, String name) {

    public static CustomerResponse from(Customer customer) {
        return new CustomerResponse(customer.getId(), customer.getEmail(), customer.getName());
    }
}
