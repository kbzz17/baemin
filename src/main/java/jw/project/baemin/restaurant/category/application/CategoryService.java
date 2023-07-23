package jw.project.baemin.restaurant.category.application;

import jw.project.baemin.restaurant.category.domain.Category;
import jw.project.baemin.restaurant.category.infrastructure.CategoryRepository;
import jw.project.baemin.restaurant.category.presentation.request.CreateCategoryRequest;
import jw.project.baemin.restaurant.category.presentation.response.CategoryResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryResponse createCategory(CreateCategoryRequest request) {
        Category category = request.toEntity();
        return CategoryResponse.from(categoryRepository.save(category));
    }
}