package jw.project.baemin.Region.application;

import jw.project.baemin.region.application.RegionService;
import jw.project.baemin.region.domain.RegionCode;
import jw.project.baemin.region.repository.RegionRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
@ActiveProfiles("test")
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class RegionServiceTest {

    @Autowired
    private RegionService regionService;

    @Autowired
    RegionRepository regionRepository;

    @Test
    @DisplayName("행정 구역 코드 파일 DB 정상 등록 테스트")
    void SaveRegionCodeTest() {
        long count = regionRepository.count();

        Assertions.assertThat(count).isEqualTo(493);
    }

    @Test
    @DisplayName("findByRegionAddress 테스트 ")
    void findByRegionAddressTest() {
        //when
        RegionCode address = regionService.findByRegionAddress("서울특별시 중랑구 면목동");

        //then
        Assertions.assertThat("1126010100").isEqualTo(address.getCode());
    }
}
