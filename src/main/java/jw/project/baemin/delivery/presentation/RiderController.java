package jw.project.baemin.delivery.presentation;

import jw.project.baemin.common.ApiResponse;
import jw.project.baemin.delivery.application.RiderService;
import jw.project.baemin.delivery.presentation.request.CreateRiderRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/rider")
public class RiderController {

    private final RiderService riderService;

    @PostMapping
    public ApiResponse<?> createRider(@RequestBody CreateRiderRequest request) {
        return ApiResponse.success(riderService.createRider(request));
    }

    @GetMapping("/{riderId}")
    public ApiResponse<?> findRider(@PathVariable Long riderId) {
        return ApiResponse.success(riderService.findRider(riderId));
    }

    @PutMapping("/{riderId}/status")
    public ApiResponse<?> changeStatus(@PathVariable Long riderId, @RequestParam boolean status) {
        riderService.changeWorkingNow(riderId, status);
        return ApiResponse.success(null);
    }

    @DeleteMapping("/{riderId}")
    public ApiResponse<?> deleteRider(@PathVariable Long riderId) {
        riderService.deleteRider(riderId);
        return ApiResponse.success(null);
    }

}
