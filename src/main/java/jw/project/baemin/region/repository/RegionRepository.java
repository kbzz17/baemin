package jw.project.baemin.region.repository;

import java.util.Optional;
import jw.project.baemin.region.domain.RegionCode;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegionRepository extends JpaRepository<RegionCode, Long> {
    Optional<RegionCode> findByRegionAddress(String regionAddress);
}
