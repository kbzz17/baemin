package jw.project.baemin.customer.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.time.LocalDateTime;
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
public class CustomerAddress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String addressName;

    private String regionAddress;

    private String detailAddress;

    private Boolean mainAddress;

    private Long regionId;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @CreationTimestamp
    private LocalDateTime createdAt;

    public void setRegionId(Long regionId) {
        this.regionId = regionId;
    }

    private static String registerWithDefaultName(String addressName, String fullAddress) {
        if (addressName.isBlank()) {
            return fullAddress;
        }
        return addressName;
    }

    public String getFullAddress() {
        return this.regionAddress + " " + this.detailAddress;
    }
}
