package jw.project.baemin.restaurant.category.application;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

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
}
