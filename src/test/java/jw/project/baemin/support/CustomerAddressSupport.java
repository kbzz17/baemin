package jw.project.baemin.support;

import jw.project.baemin.customer.domain.CustomerAddress;

public class CustomerAddressSupport {

    private static final String addressName = "Home";
    private static final String detailAddress = "빙구아파트 101호";
    private static final String regionAddress = "서울특별시 중랑구 면목동";
    private static final boolean isMainAddress = true;

    public static CustomerAddress get(Long id) {
        return CustomerAddress.builder()
            .id(id)
            .addressName(addressName)
            .regionAddress(regionAddress)
            .detailAddress(detailAddress)
            .mainAddress(isMainAddress)
            .build();
    }

    public static CustomerAddress get(Long id, String addressName, String regionAddress) {
        return CustomerAddress.builder()
            .id(id)
            .addressName(addressName)
            .regionAddress(regionAddress)
            .detailAddress(detailAddress)
            .mainAddress(isMainAddress)
            .build();
    }
}
