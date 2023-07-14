package jw.project.baemin.customer.presentation.request;

import jw.project.baemin.customer.domain.Customer;

public record UpdateCustomerRequest(String name, String email, String password) {

    public Customer toEntity() {
        return Customer.builder()
            .name(this.name)
            .email(this.email)
            .password(this.password)
            .build();
    }
}
