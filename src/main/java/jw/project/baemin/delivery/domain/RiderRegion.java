package jw.project.baemin.delivery.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jw.project.baemin.region.domain.RegionCode;
import lombok.Builder;
import lombok.Getter;

@Entity
@Getter
public class RiderRegion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Rider rider;

    @ManyToOne
    private RegionCode regionCode;

    public RiderRegion() {
    }

    @Builder
    public RiderRegion(Long id, Rider rider, RegionCode regionCode) {
        this.id = id;
        this.rider = rider;
        this.regionCode = regionCode;
    }
}
