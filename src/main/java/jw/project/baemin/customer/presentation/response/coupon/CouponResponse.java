package jw.project.baemin.customer.presentation.response.coupon;

import java.time.LocalDateTime;
import jw.project.baemin.customer.domain.Coupon;
import jw.project.baemin.customer.domain.enums.CouponType;

public record CouponResponse(Long id, String name, String code, CouponType couponType,
                             Integer discountAmount, LocalDateTime expiredDate) {

    public static CouponResponse from(Coupon coupon) {
        return new CouponResponse(coupon.getId(), coupon.getName(), coupon.getCode(),
            coupon.getCouponType(), coupon.getDiscountAmount(), coupon.getExpiredDate());
    }
}
