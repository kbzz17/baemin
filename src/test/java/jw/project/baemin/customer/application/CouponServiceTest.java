package jw.project.baemin.customer.application;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import jw.project.baemin.customer.domain.Coupon;
import jw.project.baemin.customer.domain.Customer;
import jw.project.baemin.customer.domain.enums.CouponType;
import jw.project.baemin.customer.infrastructure.CouponRepository;
import jw.project.baemin.customer.infrastructure.CustomerRepository;
import jw.project.baemin.customer.presentation.request.coupon.CreateCouponRequest;
import jw.project.baemin.customer.presentation.response.coupon.CouponResponse;
import jw.project.baemin.support.CouponSupport;
import jw.project.baemin.support.CustomerSupport;
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
public class CouponServiceTest {

    @Autowired
    CouponService couponService;

    @MockBean
    CouponRepository couponRepository;

    @MockBean
    CustomerRepository customerRepository;

    @Test
    @DisplayName("쿠폰 생성 기능 테스트")
    void createCouponTest() {
        CreateCouponRequest request = new CreateCouponRequest("말복쿠폰", CouponType.FIXED, 3000,
            LocalDateTime.now().withYear(2024));

        Coupon coupon = CouponSupport.get(1L);

        given(couponRepository.save(any())).willReturn(coupon);

        CouponResponse savedCoupon = couponService.createCoupon(request);

        assertThat(savedCoupon).extracting("name", "discountAmount", "couponType")
            .contains("말복쿠폰", 3000, CouponType.FIXED);
    }

    @Test
    @DisplayName("쿠폰 등록 기능 테스트")
    void registerCouponTest() {
        Customer customer = CustomerSupport.get(1L);
        Coupon coupon = CouponSupport.get(1L);

        given(customerRepository.findById(anyLong())).willReturn(Optional.of(customer));
        given(couponRepository.findByCode(anyString())).willReturn(Optional.of(coupon));
        given(customerRepository.save(any())).willReturn(customer);

        CouponResponse result = couponService.registerCoupon(1L, "100101");

        assertThat(result).extracting("id", "couponType", "name", "code", "expiredDate",
                "discountAmount")
            .contains(1L, CouponType.FIXED, "말복쿠폰", "100101", coupon.getExpiredDate(), 3000);
    }

    @Test
    @DisplayName("쿠폰 검색 기능 테스트")
    void findCouponTest() {
        Coupon coupon = CouponSupport.get(1L);

        given(couponRepository.findById(anyLong())).willReturn(Optional.of(coupon));

        CouponResponse result = couponService.findCoupon(1L);

        assertThat(result).extracting("id", "couponType", "name", "code", "expiredDate",
                "discountAmount")
            .contains(1L, coupon.getCouponType(), coupon.getName(), coupon.getCode(),
                coupon.getExpiredDate(), coupon.getDiscountAmount());
    }

    @Test
    @DisplayName("유저의 모든 쿠폰 검색하는 기능 테스트")
    void findUserCoupons() {
        Customer customer = CustomerSupport.get(1L);

        Coupon coupon1 = CouponSupport.get(1L);
        Coupon coupon2 = CouponSupport.get(2L);

        customer.addCoupon(coupon1);
        customer.addCoupon(coupon2);

        given(customerRepository.findById(anyLong())).willReturn(Optional.of(customer));

        List<CouponResponse> userCoupons = couponService.findUserCoupons(1L);

        assertThat(userCoupons).hasSize(2);
        assertThat(userCoupons).contains(CouponResponse.from(coupon1),
            CouponResponse.from(coupon2));

    }
}
