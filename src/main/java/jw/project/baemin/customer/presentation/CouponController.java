package jw.project.baemin.customer.presentation;

import jw.project.baemin.common.ApiResponse;
import jw.project.baemin.customer.application.CouponService;
import jw.project.baemin.customer.presentation.request.coupon.CreateCouponRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/coupon/")
public class CouponController {

    private final CouponService couponService;

    @PostMapping
    public ApiResponse<?> createCoupon(@RequestBody CreateCouponRequest request) {
        return ApiResponse.success(couponService.createCoupon(request));
    }

    @PostMapping("/register/{customerId}/code/{code}")
    public ApiResponse<?> registerCoupon(@PathVariable Long customerId, @PathVariable String code) {
        return ApiResponse.success(couponService.registerCoupon(customerId, code));
    }

    @GetMapping("/{couponId}")
    public ApiResponse<?> findCoupon(@PathVariable Long couponId) {
        return ApiResponse.success(couponService.findCoupon(couponId));
    }

    @GetMapping("/customer/{customerId}")
    public ApiResponse<?> findCustomerCoupons(@PathVariable Long customerId) {
        return ApiResponse.success(couponService.findUserCoupons(customerId));
    }
}
