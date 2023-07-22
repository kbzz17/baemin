package jw.project.baemin.customer.application;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import jw.project.baemin.customer.infrastructure.CustomerRepository;
import jw.project.baemin.customer.presentation.request.CreateCustomerRequest;
import jw.project.baemin.customer.presentation.request.UpdateCustomerRequest;
import jw.project.baemin.customer.presentation.response.CustomerResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
@ActiveProfiles("test")
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class CustomerServiceTest {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private CustomerRepository customerRepository;

    private static CustomerResponse customer;

    @BeforeEach
    void createCustomer() {
        CreateCustomerRequest request = new CreateCustomerRequest(
            "abcd@abcd.com",
            "yjw",
            "12341234");

        customer = customerService.createCustomer(request);

        assertThat(customer)
            .extracting("email", "name")
            .contains("abcd@abcd.com", "yjw");
    }

    @Test
    @DisplayName("고객 정보 열람")
    void getCustomer() {
        //given
        Long customerId = customer.userId();

        //when
        CustomerResponse customer1 = customerService.getCustomer(customerId);

        assertThat(customer1)
            .extracting("email", "name")
            .contains("abcd@abcd.com", "yjw");
    }

    @Test
    @DisplayName("고객 정보 수정 테스트")
    void updateCustomer() {
        //given
        Long customerId = customer.userId();
        UpdateCustomerRequest request = new UpdateCustomerRequest("hbjh", "efg@efg.com",
            "12345678");

        //when
        CustomerResponse updateCustomer = customerService.updateCustomer(customerId, request);

        //then
        assertThat(updateCustomer)
            .extracting("email", "name", "userId")
            .contains("efg@efg.com", "hbjh", customerId);
    }

    @Test
    @DisplayName("고객 회원 탈퇴 테스트")
    void deleteCustomer() {
        //given
        Long customerId = customer.userId();

        //when
        customerService.deleteCustomer(customerId);

        //then
        assertThatThrownBy(() -> customerService.getCustomer(customerId)).isInstanceOf(
            RuntimeException.class);
    }
}
