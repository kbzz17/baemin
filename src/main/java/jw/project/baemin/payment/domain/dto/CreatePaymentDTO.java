package jw.project.baemin.payment.domain.dto;

public record CreatePaymentDTO(int price, String paymentType, Long orderId) {

}
