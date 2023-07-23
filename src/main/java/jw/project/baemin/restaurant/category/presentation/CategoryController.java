package jw.project.baemin.restaurant.category.presentation;

import jw.project.baemin.common.ApiResponse;
import jw.project.baemin.restaurant.category.application.CategoryService;
import jw.project.baemin.restaurant.category.presentation.request.CreateCategoryRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/category")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping
    public ApiResponse<?> createCategory(@RequestBody CreateCategoryRequest request){
        return ApiResponse.success(categoryService.createCategory(request));
    }

}
