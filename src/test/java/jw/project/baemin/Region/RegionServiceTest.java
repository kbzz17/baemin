package jw.project.baemin.Region;

import jw.project.baemin.region.application.RegionService;
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

}
