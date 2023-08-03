package jw.project.baemin.restaurant.delivery.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jw.project.baemin.region.domain.RegionCode;
import jw.project.baemin.restaurant.restaurant.domain.Restaurant;
import lombok.Builder;
import lombok.Getter;

@Entity
@Getter
public class DeliveryRegion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "region_code_id", nullable = false)
    private RegionCode regionCode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id", nullable = false)
    private Restaurant restaurant;

    private Integer deliveryFee;

    public static DeliveryRegion create(RegionCode regionCode, Restaurant restaurant,
        Integer deliveryFee) {
        return new DeliveryRegion(regionCode, restaurant, deliveryFee);
    }

    public DeliveryRegion() {
    }

    private DeliveryRegion(RegionCode regionCode, Restaurant restaurant, Integer deliveryFee) {
        this.regionCode = regionCode;
        this.restaurant = restaurant;
        this.deliveryFee = deliveryFee;
    }

    @Builder
    public DeliveryRegion(Long id, RegionCode regionCode, Restaurant restaurant,
        Integer deliveryFee) {
        this.id = id;
        this.regionCode = regionCode;
        this.restaurant = restaurant;
        this.deliveryFee = deliveryFee;
    }
}
