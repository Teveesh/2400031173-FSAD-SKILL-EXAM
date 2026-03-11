package com.klef.fsad.exam.service;

import com.klef.fsad.exam.model.Payment;
import com.klef.fsad.exam.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    // Add Payment (POST)
    public Payment addPayment(Payment payment) {
        if (payment.getPaymentId() == null || payment.getPaymentId().isEmpty()) {
            throw new IllegalArgumentException("Payment ID must not be null or empty");
        }
        return paymentRepository.save(payment);
    }

    // Get All Payments (GET)
    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }

    // Get Payment by ID (GET)
    public Optional<Payment> getPaymentById(String paymentId) {
        return paymentRepository.findById(paymentId);
    }
}
