package jw.project.baemin.restaurant.category.infrastructure;

import jw.project.baemin.restaurant.category.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}
