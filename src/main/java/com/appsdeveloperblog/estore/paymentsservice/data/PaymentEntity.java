package com.appsdeveloperblog.estore.paymentsservice.data;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "payments")
public class PaymentEntity {
    private static final long serialVersionUID = 1L;
    @Id
    private String paymentId;

    @Column
    public String orderId;
}
