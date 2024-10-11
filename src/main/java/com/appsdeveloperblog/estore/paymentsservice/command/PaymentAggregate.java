package com.appsdeveloperblog.estore.paymentsservice.command;

import com.appsdeveloperblog.estore.core.commands.ProcessPaymentCommand;
import com.appsdeveloperblog.estore.core.events.PaymentProcessedEvent;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

@Aggregate
public class PaymentAggregate {
    // Data fields
    @AggregateIdentifier
    private String paymentId;
    private String orderId;

    // Constructors
    public PaymentAggregate() {
    }

    public PaymentAggregate(ProcessPaymentCommand processPaymentCommand) {
        if
        (processPaymentCommand.getPaymentDetails() == null) {
            throw new IllegalArgumentException("Missing payment details");
        }

        if (processPaymentCommand.getOrderId() == null) {
            throw new IllegalArgumentException("Missing orderId");
        }

        if (processPaymentCommand.getPaymentId() == null) {
            throw new IllegalArgumentException("Missing paymentId");
        }

        AggregateLifecycle.apply(new PaymentProcessedEvent(processPaymentCommand.getOrderId(),
                processPaymentCommand.getPaymentId()));
    }

    @EventSourcingHandler
    protected void on(PaymentProcessedEvent event) {
        this.paymentId = event.getPaymentId();
        this.orderId = event.getOrderId();
    }
}
