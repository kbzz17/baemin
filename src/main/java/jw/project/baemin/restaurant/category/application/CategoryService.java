package jw.project.baemin.restaurant.category.application;

import java.util.List;
import java.util.stream.Collectors;
import jw.project.baemin.restaurant.category.domain.Category;
import jw.project.baemin.restaurant.category.infrastructure.CategoryRepository;
import jw.project.baemin.restaurant.category.presentation.request.CreateCategoryRequest;
import jw.project.baemin.restaurant.category.presentation.request.UpdateCategoryRequest;
import jw.project.baemin.restaurant.category.presentation.response.CategoryResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryResponse createCategory(CreateCategoryRequest request) {
        Category category = request.toEntity();
        return CategoryResponse.from(categoryRepository.save(category));
    }

    public CategoryResponse findCategory(Long categoryId) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(
            RuntimeException::new);
        return CategoryResponse.from(category);
    }

    public List<CategoryResponse> findAllCategories() {
        return categoryRepository.findAll()
            .stream()
            .map(CategoryResponse::from)
            .collect(Collectors.toList());
    }

    public CategoryResponse updateCategory(Long categoryId, UpdateCategoryRequest request) {
        Category category = categoryRepository.findById(categoryId)
            .orElseThrow(RuntimeException::new);
        category.update(request.name());
        return CategoryResponse.from(category);
    }
}
