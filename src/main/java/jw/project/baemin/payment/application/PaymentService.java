package jw.project.baemin.payment.application;

import jw.project.baemin.order.application.OrderService;
import jw.project.baemin.order.domain.Order;
import jw.project.baemin.order.domain.enums.PaymentType;
import jw.project.baemin.payment.domain.Payment;
import jw.project.baemin.payment.domain.dto.CreatePaymentDTO;
import jw.project.baemin.payment.domain.enums.PaymentStatus;
import jw.project.baemin.payment.infrastructure.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final OrderService orderService;
    private final PaymentRepository paymentRepository;

    public Long payOrder(CreatePaymentDTO paymentDTO) {
        Order order = orderService.findOrder(paymentDTO.orderId());
        Payment payment = Payment.builder()
            .price(paymentDTO.price())
            .paymentType(PaymentType.valueOf(paymentDTO.paymentType()))
            .paymentStatus(PaymentStatus.NOT_PAYED)
            .build();

        payment.assignOrder(order);
        payment.changeStatus(PaymentStatus.PAYED);

        return paymentRepository.save(payment).getId();
    }

    public Payment findPayment(Long paymentId){
        return paymentRepository.findById(paymentId).orElseThrow(RuntimeException::new);
    }
}
