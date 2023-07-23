package jw.project.baemin.customer.presentation.request;

import jw.project.baemin.customer.domain.CustomerAddress;

public record UpdateCustomerAddressRequest(String addressName, String regionAddress,
                                           String detailAddress, Boolean isMainAddress) {
    public CustomerAddress toEntity() {
        return CustomerAddress.builder()
            .addressName(addressName)
            .regionAddress(regionAddress)
            .detailAddress(detailAddress)
            .mainAddress(isMainAddress)
            .build();
    }
}
