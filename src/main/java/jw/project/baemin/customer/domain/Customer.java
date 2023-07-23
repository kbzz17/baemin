package jw.project.baemin.customer.domain;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import jw.project.baemin.customer.presentation.request.UpdateCustomerRequest;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@Builder
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String password;
    private String name;


    @OneToMany(mappedBy = "customer", orphanRemoval = true, cascade = CascadeType.ALL)
    @Builder.Default
    private List<CustomerAddress> addresses = new ArrayList<>();

    @OneToMany(orphanRemoval = true)
    @Builder.Default
    private List<Coupon> coupons = new ArrayList<>();

    @CreationTimestamp
    private LocalDateTime createdAt;

    public void update(UpdateCustomerRequest request) {
        this.name = request.name();
        this.email = request.email();
        this.password = request.password();
    }

    public void addAddress(CustomerAddress customerAddress) {
        addresses.add(customerAddress);
    }

    public void removeAddress(CustomerAddress customerAddress) {
        this.addresses.remove(customerAddress);
    }

    public void addCoupon(Coupon coupon) {
        this.coupons.add(coupon);
    }
}
