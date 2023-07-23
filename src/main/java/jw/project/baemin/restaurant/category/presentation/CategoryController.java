package jw.project.baemin.restaurant.category.presentation;

import jw.project.baemin.common.ApiResponse;
import jw.project.baemin.restaurant.category.application.CategoryService;
import jw.project.baemin.restaurant.category.presentation.request.CreateCategoryRequest;
import jw.project.baemin.restaurant.category.presentation.request.UpdateCategoryRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/category")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping
    public ApiResponse<?> createCategory(@RequestBody CreateCategoryRequest request) {
        return ApiResponse.success(categoryService.createCategory(request));
    }

    @GetMapping
    public ApiResponse<?> findCategory(Long categoryId) {
        return ApiResponse.success(categoryService.findCategory(categoryId));
    }

    @GetMapping("/all")
    public ApiResponse<?> findAllCategories() {
        return ApiResponse.success(categoryService.findAllCategories());
    }

    @PutMapping("/{categoryId}")
    public ApiResponse<?> updateCategory(@PathVariable Long categoryId, @RequestBody
    UpdateCategoryRequest request) {
        return ApiResponse.success(categoryService.updateCategory(categoryId, request));
    }

    @DeleteMapping("/{categoryId}")
    public ApiResponse<?> deleteCategory(@PathVariable Long categoryId){
        return ApiResponse.success(categoryService.deleteCategory(categoryId));
    }

}
