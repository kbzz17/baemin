package jw.project.baemin.customer.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.LocalDateTime;
import jw.project.baemin.customer.domain.enums.CouponType;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Coupon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Enumerated(EnumType.STRING)
    private CouponType couponType;

    private String code;

    private Integer discountAmount;

    private Boolean isUse;

    private LocalDateTime expiredDate;

    public Boolean hasExpired() {
        return LocalDateTime.now().isAfter(this.expiredDate);
    }

    public int calculateDiscount(int price) {
        return couponType.discount(price, discountAmount);
    }

    public void used(){
        this.isUse = true;
    }

    public void canceled(){
        this.isUse = false;
    }
}
