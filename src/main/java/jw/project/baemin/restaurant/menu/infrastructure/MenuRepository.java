package jw.project.baemin.restaurant.menu.infrastructure;

import jw.project.baemin.restaurant.menu.domain.Menu;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuRepository extends JpaRepository<Menu,Long> {

}
