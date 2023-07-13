package jw.project.baemin.customer.presentation.request;

import jw.project.baemin.customer.domain.Customer;

public record CreateCustomerRequest(
    String email,
    String name,
    String password
) {
    public Customer toEntity(){
        return new Customer(
            this.email,
            this.name,
            this.password
        );
    }
}
