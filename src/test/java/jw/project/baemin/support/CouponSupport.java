package jw.project.baemin.support;

import java.time.LocalDateTime;
import jw.project.baemin.customer.domain.Coupon;
import jw.project.baemin.customer.domain.enums.CouponType;

public class CouponSupport {

    private static final CouponType couponType = CouponType.FIXED;
    private static final String name = "말복쿠폰";
    private static final String code = "100101";
    private static final LocalDateTime expiredDate = LocalDateTime.of(2024, 12, 31, 23, 59);
    private static final Integer discountAmount = 3000;
    private static final Boolean isUse = false;

    public static Coupon get(Long id) {
        return Coupon.builder()
            .id(id)
            .couponType(couponType)
            .name(name)
            .code(code)
            .expiredDate(expiredDate)
            .discountAmount(discountAmount)
            .isUse(isUse)
            .build();
    }
}
