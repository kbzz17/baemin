package jw.project.baemin.customer.infrastructure;

import jw.project.baemin.customer.domain.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CouponRepository extends JpaRepository<Coupon, Long> {

}
