package jw.project.baemin.customer.presentation.request.CustomerAddress;

import jw.project.baemin.customer.domain.CustomerAddress;

public record CreateCustomerAddressRequest(String addressName, String regionAddress,
                                           String detailAddress, Boolean mainAddress) {

    public CustomerAddress toEntity() {
        return CustomerAddress.builder()
            .addressName(this.addressName)
            .regionAddress(this.regionAddress)
            .detailAddress(this.detailAddress)
            .mainAddress(this.mainAddress)
            .build();
    }
}
