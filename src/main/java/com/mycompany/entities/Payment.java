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

    public int getId_pedido() {
        return id_pedido;
    }

    public void setId_pedido(int id_pedido) {
        this.id_pedido = id_pedido;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public PaymentData getPaymentData() {
        return paymentData;
    }

    public void setPaymentData(PaymentData paymentData) {
        this.paymentData = paymentData;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
