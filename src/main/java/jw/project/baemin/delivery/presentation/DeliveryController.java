package jw.project.baemin.delivery.presentation;

import jw.project.baemin.common.ApiResponse;
import jw.project.baemin.delivery.application.DeliveryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/delivery")
public class DeliveryController {

    private final DeliveryService deliveryService;

    @GetMapping("/{deliveryId}")
    public ApiResponse<?> findDeliveryById(@PathVariable Long id) {
        deliveryService.findDeliveryById(id);
        return ApiResponse.success(null);
    }
}
