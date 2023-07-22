package jw.project.baemin.customer.application;

import static org.assertj.core.api.Assertions.as;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.doNothing;

import java.util.List;
import java.util.Optional;
import jw.project.baemin.customer.domain.Customer;
import jw.project.baemin.customer.domain.CustomerAddress;
import jw.project.baemin.customer.infrastructure.CustomerAddressRepository;
import jw.project.baemin.customer.infrastructure.CustomerRepository;
import jw.project.baemin.customer.presentation.request.CustomerAddress.CreateCustomerAddressRequest;
import jw.project.baemin.customer.presentation.request.UpdateCustomerAddressRequest;
import jw.project.baemin.customer.presentation.response.CustomerAddress.CustomerAddressResponse;
import jw.project.baemin.support.CustomerAddressSupport;
import jw.project.baemin.support.CustomerSupport;
import jw.project.baemin.region.application.RegionService;
import jw.project.baemin.region.domain.RegionCode;
import jw.project.baemin.support.RegionSupport;
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
    CustomerAddressRepository customerAddressRepository;

    @MockBean
    RegionService regionService;

    @Test
    @DisplayName("Customer Address 추가 로직 테스트")
    void createCustomerAddressTest() {
        // Given
        Long customerId = 1L;
        String addressName = "서울특별시 중랑구 면목동";
        String detailAddress = "빙구아파트 101호";
        String regionAddress = "서울특별시 중랑구 면목동";
        boolean isMainAddress = true;

        Customer customer = CustomerSupport.get(customerId);

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
        given(regionService.findByRegionAddress(anyString())).willReturn(regionCode);
        given(customerRepository.save(any())).willReturn(customer);

        // When
        CustomerAddressResponse createCustomerAddressResponse = customerAddressService.createCustomerAddress(
            customerId, request);

        // Then
        assertThat(createCustomerAddressResponse.name()).isEqualTo(addressName);
        assertThat(createCustomerAddressResponse.fullAddress()).isEqualTo(
            regionAddress + " " + detailAddress);
        assertThat(createCustomerAddressResponse.mainAddress()).isEqualTo(isMainAddress);
    }

    @Test
    @DisplayName("고객이 등록한 모든 주소들을 검색하는 기능")
    void findCustomerAddresses() {
        //given
        Customer customer = CustomerSupport.get(1L);

        CustomerAddress address1 = CustomerAddressSupport.get(1L);
        CustomerAddress address2 = CustomerAddressSupport.get(2L, "Office", "서울특별시 광진구 중곡동");

        customer.addAddress(address1);
        customer.addAddress(address2);

        given(customerRepository.findById(1L)).willReturn(Optional.of(customer));

        //when
        List<CustomerAddressResponse> customerAddresses = customerAddressService.findCustomerAddresses(
            1L);

        //then
        assertThat(customerAddresses).hasSize(2);
        assertThat(customerAddresses).contains(CustomerAddressResponse.from(address1),
            CustomerAddressResponse.from(address2));
    }

    @Test
    @DisplayName("Customer Address 수정 로직 테스트")
    void updateCustomerAddress() {
        CustomerAddress customerAddress = CustomerAddressSupport.get(1L);

        RegionCode regionCode = RegionSupport.get(1L);

        UpdateCustomerAddressRequest request = new UpdateCustomerAddressRequest(
            "Office",
            "서울특별시 성북구 돈암동",
            "빙구아파트 2층",
            false);

        given(customerAddressRepository.findById(anyLong())).willReturn(
            Optional.of(customerAddress));
        given(regionService.findByRegionAddress(anyString())).willReturn(regionCode);
        given(customerAddressRepository.save(any())).willReturn(customerAddress);

        CustomerAddressResponse updateResponse = customerAddressService.updateCustomerAddress(
            1L, request);

        assertThat(updateResponse)
            .extracting("name", "fullAddress", "mainAddress")
            .contains("Office", "서울특별시 성북구 돈암동 빙구아파트 2층", false);
    }

    @Test
    @DisplayName("Customer Address 삭제 로직 테스트")
    void removeCustomerAddress() {
        Customer customer = CustomerSupport.get(1L);
        CustomerAddress address1 = CustomerAddressSupport.get(1L);
        CustomerAddress address2 = CustomerAddressSupport.get(2L, "Office", "서울특별시 광진구 중곡동");
        customer.addAddress(address1);
        customer.addAddress(address2);

        given(customerRepository.findById(1L)).willReturn(Optional.of(customer));
        given(customerAddressRepository.findById(1L)).willReturn(Optional.of(address1));

        customerAddressService.deleteCustomerAddress(1L, 1L);

        assertThat(customer.getAddresses()).hasSize(1);
        assertThat(customer.getAddresses()).doesNotContain(address1);
        assertThat(customer.getAddresses()).contains(address2);
    }
}
