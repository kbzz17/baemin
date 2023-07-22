package jw.project.baemin.customer.presentation.response.CustomerAddress;

import jw.project.baemin.customer.domain.CustomerAddress;

public record CustomerAddressResponse(Long id, String name, String fullAddress,
                                      Boolean mainAddress) {

    public static CustomerAddressResponse from(CustomerAddress address) {
        return new CustomerAddressResponse(
            address.getId(),
            address.getAddressName(),
            address.getFullAddress(),
            address.getMainAddress()
        );
    }
}
