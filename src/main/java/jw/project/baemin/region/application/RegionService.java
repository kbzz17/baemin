package jw.project.baemin.region.application;

import jakarta.annotation.PostConstruct;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import jw.project.baemin.common.FileUtil;
import jw.project.baemin.region.domain.RegionCode;
import jw.project.baemin.region.repository.RegionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class RegionService {

    private final RegionRepository regionRepository;

    @PostConstruct
    @Transactional
    public void init() {
        if (regionRepository.count() == 0) {
            regionRepository.saveAll(parseRegions());
        }
    }

    private List<RegionCode> parseRegions() {
        String regionFileName = "RegionFile.txt";
        return Objects.requireNonNull(FileUtil.parseTxtFile(regionFileName))
            .stream()
            .map(this::convertToRegionCode)
            .collect(Collectors.toList());
    }

    private RegionCode convertToRegionCode(String[] regions) {
        return RegionCode.builder()
            .code(regions[0])
            .regionAddress(regions[1])
            .build();
    }
}
