package jw.project.baemin.customer.application;

import jw.project.baemin.customer.domain.Coupon;
import jw.project.baemin.customer.domain.Customer;
import jw.project.baemin.customer.infrastructure.CouponRepository;
import jw.project.baemin.customer.infrastructure.CustomerRepository;
import jw.project.baemin.customer.presentation.request.coupon.CreateCouponRequest;
import jw.project.baemin.customer.presentation.response.CustomerResponse;
import jw.project.baemin.customer.presentation.response.coupon.CouponResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CouponService {

    private final CouponRepository couponRepository;

    private final CustomerRepository customerRepository;

    public CouponResponse createCoupon(CreateCouponRequest request) {
        Coupon coupon = request.toEntity();
        if (coupon.hasExpired()){
            throw new RuntimeException();
        }
        coupon = couponRepository.save(coupon);
        return CouponResponse.from(coupon);
    }

    public CouponResponse registerCoupon(Long customerId, String code) {
        Customer customer = customerRepository.findById(customerId)
            .orElseThrow(RuntimeException::new);
        Coupon coupon = couponRepository.findByCode(code).orElseThrow(RuntimeException::new);

        if (coupon.hasExpired()) {
            throw new RuntimeException("만료된 쿠폰입니다.");
        }
        customer.addCoupon(coupon);
        customerRepository.save(customer);

        return CouponResponse.from(coupon);
    }
}
