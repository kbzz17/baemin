package jw.project.baemin.customer.presentation.request.coupon;

import java.time.LocalDateTime;
import java.util.UUID;
import jw.project.baemin.customer.domain.Coupon;
import jw.project.baemin.customer.domain.enums.CouponType;
import lombok.Getter;

@Getter
public record CreateCouponRequest(String name, CouponType couponType,
                                  Integer discountAmount, LocalDateTime expiredDate) {

    public Coupon toEntity() {
        return Coupon.builder()
            .code(UUID.randomUUID().toString())
            .name(name)
            .couponType(couponType)
            .discountAmount(discountAmount)
            .isUse(false)
            .expiredDate(expiredDate)
            .build();
    }
}
