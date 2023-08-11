package jw.project.baemin.support;

import jw.project.baemin.restaurant.menu.domain.Menu;
import jw.project.baemin.restaurant.menu.domain.MenuGroup;
import jw.project.baemin.restaurant.menu.domain.enums.MenuStatus;

public class MenuSupport {
    public static int price = 1000;
    public static String description = "맛있는 메뉴";
    public static String name = "chicken";
    public static MenuStatus menuStatus = MenuStatus.SALE;
    public static MenuGroup menuGroup = MenuGroup.builder()
        .id(1L)
        .name("치킨")
        .description("치킨종류")
        .shopId(1L)
        .priority(1)
        .build();

    public static Menu get(Long id) {
        return Menu.builder()
            .id(id)
            .price(price)
            .description(description)
            .name(name)
            .menuStatus(menuStatus)
            .menuGroup(menuGroup)
            .build();
    }
}
