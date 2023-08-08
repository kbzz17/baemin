package jw.project.baemin.delivery.domain;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Entity
@Getter
public class Rider {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    private String password;
    private boolean workingNow;

    @OneToMany(mappedBy = "rider", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<Delivery> deliveries;

    @OneToMany(mappedBy = "rider", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<RiderRegion> riderRegions;

    public void changeWorkingNow(boolean workingNow) {
        this.workingNow = workingNow;
    }

    public Rider() {
    }

    @Builder
    public Rider(Long id, String email, String password, boolean workingNow,
        List<Delivery> deliveries,
        List<RiderRegion> riderRegions) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.workingNow = workingNow;
        this.deliveries = deliveries;
        this.riderRegions = riderRegions;
    }
}
