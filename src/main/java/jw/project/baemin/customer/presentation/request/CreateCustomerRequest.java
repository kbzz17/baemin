package jw.project.baemin.customer.presentation.request;

import jw.project.baemin.customer.domain.Customer;

public record CreateCustomerRequest(
    String email,
    String name,
    String password
) {

    public Customer toEntity() {
        return Customer.builder()
            .email(this.email)
            .name(this.name)
            .password(this.password)
            .build();
    }
}
