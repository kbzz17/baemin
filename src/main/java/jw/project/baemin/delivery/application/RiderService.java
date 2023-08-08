package jw.project.baemin.delivery.application;

import jw.project.baemin.delivery.domain.Rider;
import jw.project.baemin.delivery.infrastructure.RiderRepository;
import jw.project.baemin.delivery.presentation.request.CreateRiderRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RiderService {

    private final RiderRepository riderRepository;

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
}
