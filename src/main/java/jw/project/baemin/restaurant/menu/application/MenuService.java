package jw.project.baemin.restaurant.menu.application;

import java.util.List;
import java.util.stream.Collectors;
import jw.project.baemin.restaurant.menu.domain.Menu;
import jw.project.baemin.restaurant.menu.domain.MenuGroup;
import jw.project.baemin.restaurant.menu.infrastructure.MenuGroupRepository;
import jw.project.baemin.restaurant.menu.infrastructure.MenuRepository;
import jw.project.baemin.restaurant.menu.presentation.request.CreateMenuRequest;
import jw.project.baemin.restaurant.menu.presentation.response.MenuResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MenuService {

    private final MenuRepository menuRepository;

    private final MenuGroupRepository menuGroupRepository;

    private final MenuGroupService menuGroupService;

    public MenuResponse createMenu(Long menuGroupId, CreateMenuRequest request) {
        MenuGroup menuGroup = menuGroupRepository.findById(menuGroupId)
            .orElseThrow(RuntimeException::new);
        Menu menu = request.toEntity();

        menuGroup.addMenu(menu);
        return MenuResponse.from(menu);
    }

    public List<MenuResponse> findMenusByMenuGroupId(Long menuGroupId) {
        MenuGroup menuGroup = menuGroupRepository.findById(menuGroupId)
            .orElseThrow(RuntimeException::new);
        return menuGroup.getMenus()
            .stream()
            .map(MenuResponse::from)
            .collect(Collectors.toList());
    }

    public MenuResponse findMenu(Long menuId) {
        return MenuResponse.from(
            menuRepository.findById(menuId).orElseThrow(RuntimeException::new));
    }

    public void deleteMenu(Long menuId) {
        menuRepository.deleteById(menuId);
    }

}
