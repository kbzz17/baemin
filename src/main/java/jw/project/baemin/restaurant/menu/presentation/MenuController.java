package jw.project.baemin.restaurant.menu.presentation;

import java.nio.file.Path;
import jw.project.baemin.common.ApiResponse;
import jw.project.baemin.restaurant.menu.application.MenuService;
import jw.project.baemin.restaurant.menu.presentation.request.CreateMenuRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/menu")
public class MenuController {

    private final MenuService menuService;

    @PostMapping("/menuGroup/{menuGroupId}")
    public ApiResponse<?> createMenu(@PathVariable Long menuGroupId,
        @RequestBody CreateMenuRequest request) {
        return ApiResponse.success(menuService.createMenu(menuGroupId, request));
    }

    @GetMapping("/{menuId}")
    public ApiResponse<?> findMenu(@PathVariable Long menuId) {
        return ApiResponse.success(menuService.findMenu(menuId));
    }

    @GetMapping("/menuGroup/{menuGroupId}")
    public ApiResponse<?> findMenusByMenuGroupId(@PathVariable Long menuGroupId) {
        return ApiResponse.success(menuService.findMenusByMenuGroupId(menuGroupId));
    }

    @DeleteMapping("/{menuId}")
    public ApiResponse<?> deleteMenu(@PathVariable Long menuId) {
        menuService.deleteMenu(menuId);

        return ApiResponse.success(null);
    }
}
