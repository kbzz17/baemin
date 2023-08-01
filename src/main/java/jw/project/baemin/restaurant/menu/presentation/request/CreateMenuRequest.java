package jw.project.baemin.restaurant.menu.presentation.request;

import jw.project.baemin.restaurant.menu.domain.Menu;
import jw.project.baemin.restaurant.menu.domain.enums.MenuStatus;

public record CreateMenuRequest(String name, String description, Integer price,
                                MenuStatus menuStatus) {

    public Menu toEntity() {
        return Menu.builder()
            .name(name)
            .description(description)
            .price(price)
            .menuStatus(menuStatus)
            .build();
    }
}
