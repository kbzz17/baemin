package jw.project.baemin.customer.application;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;

import java.time.LocalDateTime;
import java.util.Optional;
import jw.project.baemin.customer.domain.Coupon;
import jw.project.baemin.customer.domain.enums.CouponType;
import jw.project.baemin.customer.infrastructure.CouponRepository;
import jw.project.baemin.customer.presentation.request.coupon.CreateCouponRequest;
import jw.project.baemin.customer.presentation.response.coupon.CouponResponse;
import org.assertj.core.api.Assertions;
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

    @Test
    @DisplayName("쿠폰 생성 기능 테스트")
    void createCouponTest() {
        CreateCouponRequest request = new CreateCouponRequest("말복쿠폰", CouponType.FIXED, 3000,
            LocalDateTime.now());

        Coupon coupon = Coupon.builder()
            .id(1L)
            .couponType(CouponType.FIXED)
            .name("말복쿠폰")
            .code("100101")
            .expiredDate(LocalDateTime.now())
            .discountAmount(3000)
            .isUse(false)
            .build();

        given(couponRepository.save(any())).willReturn(coupon);

        CouponResponse savedCoupon = couponService.createCoupon(request);

        assertThat(savedCoupon).extracting("name", "discountAmount", "couponType")
            .contains("말복쿠폰", 3000, CouponType.FIXED);
    }
}
