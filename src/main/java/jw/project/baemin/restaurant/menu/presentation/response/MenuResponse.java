package jw.project.baemin.restaurant.menu.presentation.response;

import jw.project.baemin.restaurant.menu.domain.Menu;
import jw.project.baemin.restaurant.menu.domain.enums.MenuStatus;

public record MenuResponse(Long menuId, String name, String description, Integer price,
                           MenuStatus menuStatus) {

    public static MenuResponse from(Menu menu) {
        return new MenuResponse(
            menu.getId(),
            menu.getName(),
            menu.getDescription(),
            menu.getPrice(),
            menu.getMenuStatus()
        );
    }

}
