package com.mycompany.entities;

import jakarta.persistence.*;

@Entity(name="tb_payment_data")
public abstract class PaymentData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public PaymentData(Long id) {
        this.id = id;
    }

    public PaymentData() {

    }
}

