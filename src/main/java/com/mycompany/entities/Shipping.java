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

    private String adress;

    private String city;

    private String state;

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

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
