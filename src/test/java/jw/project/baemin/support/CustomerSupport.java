package jw.project.baemin.support;

import jw.project.baemin.customer.domain.Customer;

public class CustomerSupport {

    private static final String customerEmail = "poujis@naver.com";
    private static final String customerName = "yjw";
    private static final String customerPassword = "1234";

    public static Customer get(Long id) {
        return Customer.builder()
            .id(id)
            .name(customerName)
            .email(customerEmail)
            .password(customerPassword)
            .build();
    }
}
