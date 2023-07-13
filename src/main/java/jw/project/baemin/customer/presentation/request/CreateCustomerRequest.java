package jw.project.baemin.customer.presentation.request;

public record CreateCustomerRequest(
    String email,
    String name,
    String password
) {

}
