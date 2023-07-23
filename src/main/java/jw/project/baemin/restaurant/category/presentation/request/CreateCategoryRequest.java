package jw.project.baemin.restaurant.category.presentation.request;

import jw.project.baemin.restaurant.category.domain.Category;

public record CreateCategoryRequest(String name) {
    public Category toEntity(){
        return Category.builder()
            .name(name)
            .build();
    }

}
