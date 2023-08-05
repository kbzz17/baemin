package jw.project.baemin.delivery.infrastructure;

import jw.project.baemin.delivery.domain.Rider;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RiderRepository extends JpaRepository<Rider, Long> {

}
