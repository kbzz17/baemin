package jw.project.baemin.delivery.application;

import jw.project.baemin.delivery.domain.Delivery;
import jw.project.baemin.delivery.infrastructure.DeliveryRepository;
import jw.project.baemin.order.domain.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeliveryService {

    private final DeliveryRepository deliveryRepository;

    public void findDeliveryById(Long deliveryId) {
        deliveryRepository.findById(deliveryId).orElseThrow(RuntimeException::new);
    }

    public Delivery saveDelivery(Order order) {
        return deliveryRepository.save(
            Delivery.createDelivery(order, order.getRestaurant().getAddress(),
                order.getDeliveryAddress(), order.getDeliveryFee())
        );
    }

}
