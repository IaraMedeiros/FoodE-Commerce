package com.mycompany.entities;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "shipping")
public class Shipping {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Date timeExpected;

    @OneToOne
    @MapsId
    private Order order;

    public Shipping() {
    }

    public Shipping(Integer id, Date timeExpected, Order order) {
        this.id = id;
        this.timeExpected = timeExpected;
        this.order = order;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getTimeExpected() {
        return timeExpected;
    }

    public void setTimeExpected(Date timeExpected) {
        this.timeExpected = timeExpected;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
