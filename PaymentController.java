package com.klef.fsad.exam.controller;

import com.klef.fsad.exam.model.Payment;
import com.klef.fsad.exam.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    // POST - Add Payment
    // URL: POST http://localhost:8080/api/payments/add
    @PostMapping("/add")
    public ResponseEntity<String> addPayment(@RequestBody Payment payment) {
        try {
            paymentService.addPayment(payment);
            return new ResponseEntity<>("Payment Added Successfully", HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    // GET - Get All Payments
    // URL: GET http://localhost:8080/api/payments/all
    @GetMapping("/all")
    public ResponseEntity<List<Payment>> getAllPayments() {
        List<Payment> payments = paymentService.getAllPayments();
        return new ResponseEntity<>(payments, HttpStatus.OK);
    }

    // GET - Get Payment by ID
    // URL: GET http://localhost:8080/api/payments/{id}
    @GetMapping("/{id}")
    public ResponseEntity<?> getPaymentById(@PathVariable String id) {
        Optional<Payment> payment = paymentService.getPaymentById(id);
        if (payment.isPresent()) {
            return new ResponseEntity<>(payment.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Payment Not Found with ID: " + id, HttpStatus.NOT_FOUND);
        }
    }
}
