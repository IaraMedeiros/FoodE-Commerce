package com.mycompany.payment;

import com.mycompany.enums.PaymentMethod;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

import java.util.Date;

@Entity
public class Payment {
    @Id
    private int id_pedido;
    private double valor;
    private Date paymentDate;

    private PaymentMethod paymentMethod;
    @ManyToOne
    private PaymentData paymentData;
}
