package jw.project.baemin.restaurant.category.application;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import jw.project.baemin.restaurant.category.domain.Category;
import jw.project.baemin.restaurant.category.infrastructure.CategoryRepository;
import jw.project.baemin.restaurant.category.presentation.request.CreateCategoryRequest;
import jw.project.baemin.restaurant.category.presentation.response.CategoryResponse;
import jw.project.baemin.support.IntegrationTestSupport;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

public class CategoryServiceTest extends IntegrationTestSupport {

    @Autowired
    CategoryService categoryService;

    @MockBean
    CategoryRepository categoryRepository;


    @Test
    @DisplayName("카테고리 생성 기능 테스트")
    void createCategory() {
        CreateCategoryRequest request = new CreateCategoryRequest("한식");

        Category category = new Category(1L, "한식");

        given(categoryRepository.save(any())).willReturn(category);

        CategoryResponse response = categoryService.createCategory(request);

        assertThat(response.id()).isEqualTo(1L);
        assertThat(response.name()).isEqualTo("한식");
    }

    @Test
    @DisplayName("카테고리 검색 기능 테스트")
    void findCategory() {
        Category category = new Category(1L, "한식");

        given(categoryRepository.findById(anyLong())).willReturn(Optional.of(category));

        CategoryResponse result = categoryService.findCategory(1L);

        assertThat(result.id()).isEqualTo(1L);
        assertThat(result.name()).isEqualTo("한식");
    }

    @Test
    @DisplayName("모든 카테고리 검색 기능 테스트")
    void findAllCategories() {
        List<Category> categories = new ArrayList<>();
        Category category1 = new Category(1L, "한식");
        Category category2 = new Category(2L, "양식");
        Category category3 = new Category(3L, "중식");
        categories.add(category1);
        categories.add(category2);
        categories.add(category3);

        given(categoryRepository.findAll()).willReturn(categories);

        List<CategoryResponse> allCategories = categoryService.findAllCategories();

        assertThat(allCategories).hasSize(3);
        assertThat(allCategories).contains(CategoryResponse.from(category1),
            CategoryResponse.from(category2), CategoryResponse.from(category3));
    }
}
