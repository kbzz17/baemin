package jw.project.baemin.restaurant.menu.presentation.request;

import jw.project.baemin.restaurant.menu.domain.MenuGroup;

public record CreateMenuGroupRequest(String name, String description, Integer priority) {

    public MenuGroup toEntity() {
        return MenuGroup.builder()
            .name(name)
            .description(description)
            .priority(priority)
            .build();
    }
}
