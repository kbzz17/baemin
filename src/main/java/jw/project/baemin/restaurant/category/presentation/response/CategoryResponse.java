package jw.project.baemin.restaurant.category.presentation.response;

import jw.project.baemin.restaurant.category.domain.Category;

public record CategoryResponse(Long id, String name) {
    public static CategoryResponse from(Category category){
        return new CategoryResponse(category.getId(), category.getName());
    }
}
