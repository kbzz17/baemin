package jw.project.baemin.customer.application;

import jw.project.baemin.customer.domain.Coupon;
import jw.project.baemin.customer.infrastructure.CouponRepository;
import jw.project.baemin.customer.presentation.request.coupon.CreateCouponRequest;
import jw.project.baemin.customer.presentation.response.coupon.CouponResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CouponService {

    private final CouponRepository couponRepository;


    public CouponResponse createCoupon(CreateCouponRequest request) {
        Coupon coupon = request.toEntity();
        coupon = couponRepository.save(coupon);
        return CouponResponse.from(coupon);
    }
}
