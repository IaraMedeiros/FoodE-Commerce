package com.mycompany.order;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mycompany.OrderItem.OrderItem;
import com.mycompany.enums.OrderStatus;
import com.mycompany.user.User;
import jakarta.persistence.*;

import java.time.Instant;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name="tb_order")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private double valor;
    @Enumerated(EnumType.ORDINAL)
    private OrderStatus orderStatus;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="costumer_id")
    private User costumer;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy/MM/dd'T'HH:mm:ss'Z'", timezone = "GMT")
    private Instant moment;


    @OneToMany(mappedBy = "id.order")
    private Set<OrderItem> items = new HashSet<>();

    public Order() {
    }

    public Order(Integer id) {
        this.id = id;
    }

    public Order(Integer id, double valor, OrderStatus orderStatus, User costumer, Instant moment) {
        this.id = id;
        this.valor = valor;
        this.orderStatus = orderStatus;
        this.costumer = costumer;
        this.moment = moment;
    }

    public Double getSubTotal() {
        double subtotal = 0;
        for( OrderItem item : items ) {
            subtotal = subtotal + item.getPrice();
        }
        return subtotal;
    }
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public User getCostumer() {
        return costumer;
    }

    public void setCostumer(User costumer) {
        this.costumer = costumer;
    }

    public Instant getMoment() {
        return moment;
    }

    public void setMoment(Instant moment) {
        this.moment = moment;
    }

    public Set<OrderItem> getItems() {
        return items;
    }

    public void setItems(Set<OrderItem> items) {
        this.items = items;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(id, order.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}

