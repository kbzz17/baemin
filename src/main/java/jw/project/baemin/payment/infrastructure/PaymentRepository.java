package jw.project.baemin.payment.infrastructure;

import jw.project.baemin.payment.domain.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

}
