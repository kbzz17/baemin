package jw.project.baemin.restaurant.menu.application;

import java.util.List;
import java.util.stream.Collectors;
import jw.project.baemin.restaurant.menu.domain.MenuGroup;
import jw.project.baemin.restaurant.menu.infrastructure.MenuGroupRepository;
import jw.project.baemin.restaurant.menu.presentation.request.CreateMenuGroupRequest;
import jw.project.baemin.restaurant.menu.presentation.request.UpdateMenuGroupRequest;
import jw.project.baemin.restaurant.menu.presentation.response.MenuGroupResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MenuGroupService {

    private final MenuGroupRepository menuGroupRepository;

    public MenuGroupResponse createMenuGroup(Long shopId, CreateMenuGroupRequest request) {
        MenuGroup menuGroup = request.toEntity();
        menuGroup.setShopId(shopId);

        return MenuGroupResponse.from(menuGroupRepository.save(menuGroup));
    }

    public MenuGroupResponse findMenuGroup(Long menuGroupId) {
        return MenuGroupResponse.from(
            menuGroupRepository.findById(menuGroupId).orElseThrow(RuntimeException::new));
    }

    public List<MenuGroupResponse> findAllMenuGroup(Long shopId) {
        return menuGroupRepository.findByShopId(shopId)
            .stream()
            .map(MenuGroupResponse::from)
            .collect(Collectors.toList());
    }

    public MenuGroupResponse updateMenuGroup(Long menuGroupId, UpdateMenuGroupRequest request) {
        MenuGroup menuGroup = menuGroupRepository.findById(menuGroupId)
            .orElseThrow(RuntimeException::new);

        menuGroup.update(request);
        return MenuGroupResponse.from(menuGroup);
    }

    public void deleteMenuGroup(Long menuGroupId) {
        menuGroupRepository.deleteById(menuGroupId);

    }


}
