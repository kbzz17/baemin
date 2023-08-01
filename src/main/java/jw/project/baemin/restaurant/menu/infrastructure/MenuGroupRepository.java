package jw.project.baemin.restaurant.menu.infrastructure;

import java.util.List;
import jw.project.baemin.restaurant.menu.domain.MenuGroup;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuGroupRepository extends JpaRepository<MenuGroup, Long> {

    List<MenuGroup> findByShopId(Long shopId);
}
