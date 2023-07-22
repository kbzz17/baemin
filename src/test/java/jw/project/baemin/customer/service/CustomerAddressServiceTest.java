package jw.project.baemin.customer.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

import java.util.ArrayList;
import java.util.Optional;
import jw.project.baemin.customer.application.CustomerAddressService;
import jw.project.baemin.customer.domain.Customer;
import jw.project.baemin.customer.infrastructure.CustomerRepository;
import jw.project.baemin.customer.presentation.request.CustomerAddress.CreateCustomerAddressRequest;
import jw.project.baemin.customer.presentation.response.CustomerAddress.CustomerAddressResponse;
import jw.project.baemin.region.application.RegionService;
import jw.project.baemin.region.domain.RegionCode;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
@ActiveProfiles("test")
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
class CustomerAddressServiceTest {

    @Autowired
    CustomerAddressService customerAddressService;
    @MockBean
    CustomerRepository customerRepository;

    @MockBean
    RegionService regionService;

    @Test
    @DisplayName("Customer Address 추가 로직 테스트")
    void createCustomerAddressTest() {
        // Given
        Long customerId = 1L;
        String customerEmail = "poujis@naver.com";
        String customerName = "yjw";
        String customerPassword = "1234";
        String addressName = "Home";
        String detailAddress = "빙구아파트 101호";
        String regionAddress = "서울특별시 중랑구 면목동";
        boolean isMainAddress = true;

        Customer customer = Customer.builder()
            .id(customerId)
            .email(customerEmail)
            .name(customerName)
            .password(customerPassword)
            .addresses(new ArrayList<>())
            .build();

        CreateCustomerAddressRequest request = new CreateCustomerAddressRequest(
            addressName,
            regionAddress,
            detailAddress,
            isMainAddress
        );

        RegionCode regionCode = RegionCode.builder()
            .code("1126010100")
            .regionAddress("서울특별시 중랑구 면목동").build();

        given(customerRepository.findById(customerId)).willReturn(Optional.of(customer));
        given(regionService.findByRegionAddress(regionAddress)).willReturn(regionCode);
        given(customerRepository.save(any())).willReturn(customer);

        // When
        CustomerAddressResponse createCustomerAddressResponse = customerAddressService.createCustomerAddress(
            customerId, request);

        // Then
        assertThat(createCustomerAddressResponse.name()).isEqualTo(addressName);
        assertThat(createCustomerAddressResponse.fullAddress()).isEqualTo(regionAddress + " " + detailAddress);
        assertThat(createCustomerAddressResponse.mainAddress()).isEqualTo(isMainAddress);
    }
}
