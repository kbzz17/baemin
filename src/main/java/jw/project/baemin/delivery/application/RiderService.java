package jw.project.baemin.delivery.application;

import java.util.List;
import jw.project.baemin.delivery.domain.Rider;
import jw.project.baemin.delivery.domain.RiderRegion;
import jw.project.baemin.delivery.infrastructure.RiderRepository;
import jw.project.baemin.delivery.presentation.request.CreateRiderRequest;
import jw.project.baemin.region.application.RegionService;
import jw.project.baemin.region.domain.RegionCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class RiderService {

    private final RiderRepository riderRepository;

    private final RegionService regionService;

    public Long createRider(CreateRiderRequest request) {
        return riderRepository.save(request.toEntity()).getId();
    }

    public Rider findRider(Long riderId) {
        return riderRepository.findById(riderId).orElseThrow(RuntimeException::new);
    }

    public void changeWorkingNow(Long riderId, boolean workingNow) {
        findRider(riderId).changeWorkingNow(workingNow);
    }

    public void deleteRider(Long riderId){
        riderRepository.deleteById(riderId);
    }

    @Transactional
    public void addRiderAreaCode(Long riderId, Long regionCodeId) {
        Rider rider = findRider(riderId);
        RegionCode regionCode = regionService.findRegionById(regionCodeId);
        RiderRegion riderRegion = RiderRegion.builder()
            .rider(rider)
            .regionCode(regionCode)
            .build();
        rider.addRiderRegion(riderRegion);
    }

    @Transactional
    public void removeRiderAreaCode(Long riderId, Long regionCodeId){
        Rider rider = findRider(riderId);
        List<RiderRegion> riderRegions = rider.getRiderRegions();
        riderRegions.stream()
            .filter(riderRegion -> riderRegion.getRegionCode().getId().equals(regionCodeId))
            .findFirst()
            .ifPresent(riderRegions::remove);

    }
}
