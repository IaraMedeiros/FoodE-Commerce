package com.mycompany.entities;

import com.mycompany.entities.enums.PaymentMethod;
import jakarta.persistence.*;

import java.util.Date;

@Entity(name="tb_payment")
public class Payment {
    @Id
    private int id_pedido;
    private double valor;
    private Date paymentDate;

    private PaymentMethod paymentMethod;

    @ManyToOne
    @JoinColumn(name="payment_data_id")
    private PaymentData paymentData;

    @OneToOne
    @MapsId
    private Order order;
}
