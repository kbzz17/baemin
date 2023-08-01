package jw.project.baemin.restaurant.menu.presentation.response;

import jw.project.baemin.restaurant.menu.domain.MenuGroup;

public record MenuGroupResponse(Long menuGroupId, String name, String description,
                                Integer priority) {

    public static MenuGroupResponse from(MenuGroup menuGroup) {
        return new MenuGroupResponse(
            menuGroup.getId(),
            menuGroup.getName(),
            menuGroup.getDescription(),
            menuGroup.getPriority());
    }
}
