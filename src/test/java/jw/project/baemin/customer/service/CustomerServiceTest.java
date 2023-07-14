package jw.project.baemin.customer.service;

import static org.assertj.core.api.Assertions.assertThat;

import jw.project.baemin.customer.application.CustomerService;
import jw.project.baemin.customer.domain.Customer;
import jw.project.baemin.customer.infrastructure.CustomerRepository;
import jw.project.baemin.customer.presentation.request.CreateCustomerRequest;
import jw.project.baemin.customer.presentation.response.CustomerResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
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

    public static Customer customer;

    @BeforeEach
    void createCustomer() {
        CreateCustomerRequest request = new CreateCustomerRequest(
            "abcd@abcd.com",
            "yjw",
            "12341234");

        CustomerResponse customer1 = customerService.createCustomer(request);

        assertThat(customer1)
            .extracting("email", "name")
            .contains("abcd@abcd.com", "yjw");
    }

    @Test
    @DisplayName("고객 정보 열람")
    void getCustomer() {
        //given
        Long customerId = 1L;

        //when
        CustomerResponse customer1 = customerService.getCustomer(customerId);

        assertThat(customer1)
            .extracting("email", "name")
            .contains("abcd@abcd.com", "yjw");
    }
}
