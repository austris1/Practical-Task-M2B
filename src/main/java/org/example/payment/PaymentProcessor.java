package org.example.payment;

import org.example.model.Order;
import org.example.model.PaymentResult;

public class PaymentProcessor {
    public PaymentResult process(Order order, PaymentMethod paymentMethod){
        if (order == null) {
            return new PaymentResult(false, "No order to pay.");
        }
        if (paymentMethod == null) {
            return new PaymentResult(false, "No payment method selected.");
        }
        if (order.isPaid()) {
            return new PaymentResult(false, "Order is already paid.");
        }
        if (order.isEmpty()) {
            return new PaymentResult(false, "Cannot pay for an empty order.");
        }

        PaymentResult result = paymentMethod.pay(order.calculateTotal());

        if(result.isSuccessful()){
            order.markAsPaid();
        }

        return result;
    }
}
