package com.mycompany.order;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mycompany.OrderItem.OrderItem;
import com.mycompany.user.User;
import jakarta.persistence.*;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="tb_order")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private double valor;
    private Integer orderStatus;
    @ManyToOne
    @JoinColumn(name="cliente_id")
    private User cliente;
    @OneToMany(mappedBy = "id.order")
    private Set<OrderItem> items = new HashSet<>();

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy/MM/dd'T'HH:mm:ss'Z'", timezone = "GMT")
    private Instant moment;



}
