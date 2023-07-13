package jw.project.baemin.customer.service;

import static org.assertj.core.api.Assertions.assertThat;

import jw.project.baemin.customer.application.CustomerService;
import jw.project.baemin.customer.domain.Customer;
import jw.project.baemin.customer.infrastructure.CustomerRepository;
import jw.project.baemin.customer.presentation.request.CreateCustomerRequest;
import jw.project.baemin.customer.presentation.response.CustomerResponse;
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

    public static Customer customer;

    @Test
    @DisplayName("고객 회원가입 테스트")
    void createCustomer() {
        CreateCustomerRequest request = new CreateCustomerRequest(
            "abcd@abcd.com",
            "yjw",
            "12341234");

        CustomerResponse customer1 = customerService.createCustomer(request);

        assertThat(customer1)
            .extracting("email","name")
            .contains("abcd@abcd.com","yjw");
    }
}
