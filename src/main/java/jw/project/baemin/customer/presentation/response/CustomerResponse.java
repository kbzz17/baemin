package jw.project.baemin.customer.presentation.response;

import jw.project.baemin.customer.domain.Customer;

public record CustomerResponse(String email, String name) {

    public static CustomerResponse from(Customer customer) {
        return new CustomerResponse(customer.getEmail(), customer.getName());
    }
}
