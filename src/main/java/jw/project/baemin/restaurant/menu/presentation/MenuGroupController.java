package jw.project.baemin.restaurant.menu.presentation;

import jw.project.baemin.common.ApiResponse;
import jw.project.baemin.restaurant.menu.application.MenuGroupService;
import jw.project.baemin.restaurant.menu.presentation.request.CreateMenuGroupRequest;
import jw.project.baemin.restaurant.menu.presentation.request.UpdateMenuGroupRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/menuGroup")
public class MenuGroupController {

    private final MenuGroupService menuGroupService;

    @PostMapping("/shopId/{shopId}")
    public ApiResponse<?> createMenuGroup(Long shopId,
        @RequestBody CreateMenuGroupRequest request) {
        return ApiResponse.success(menuGroupService.createMenuGroup(shopId, request));
    }

    @GetMapping("/{menuGroupId}")
    public ApiResponse<?> findMenuGroup(Long menuGroupId) {
        return ApiResponse.success(menuGroupService.findMenuGroup(menuGroupId));
    }

    @GetMapping("/{shopId}/all")
    public ApiResponse<?> findAllMenuGroup(Long shopId) {
        return ApiResponse.success(menuGroupService.findAllMenuGroup(shopId));
    }

    @PutMapping("/{menuGroupId}")
    public ApiResponse<?> updateMenuGroup(Long menuGroupId, UpdateMenuGroupRequest request) {
        return ApiResponse.success(menuGroupService.updateMenuGroup(menuGroupId, request));
    }

    @DeleteMapping("/{menuGroupId}")
    public ApiResponse<?> deleteMenuGroup(Long menuGroupId) {
        menuGroupService.deleteMenuGroup(menuGroupId);
        return ApiResponse.success(null);
    }
}
