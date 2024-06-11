package com.mycompany.services;

import com.mycompany.entities.Payment;
import com.mycompany.entities.Product;
import com.mycompany.exceptions.PaymentNotFoundException;
import com.mycompany.exceptions.ProductNotFoundException;
import com.mycompany.repositories.PaymentRepository;
import com.mycompany.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public class PaymentService {
    @Autowired
    private PaymentRepository repo;

    public List<Payment> listAll(){
        return (List<Payment>) repo.findAll();
    }

    public void save(Payment payment) {
        repo.save(payment);
    }

    public Payment get(Integer id) throws PaymentNotFoundException {
        Optional<Payment> payment = repo.findById(id);
        if(payment.isPresent()){
            return payment.get();
        }
        throw new PaymentNotFoundException("Could not find any payments with ID " + id);
    }

    public void delete(Integer id) throws PaymentNotFoundException {
        if(id == null || id == 0) {
            throw new PaymentNotFoundException("Invalid ID: " + id);
        }

        Optional<Payment> payment = repo.findById(id);
        if(payment.isPresent()) {
            repo.deleteById(id);
        } else {
            throw new PaymentNotFoundException("Payment not found with ID: " + id);
        }
    }
}
