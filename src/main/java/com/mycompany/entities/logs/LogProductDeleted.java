package com.mycompany.entities.logs;

import jakarta.persistence.*;

import java.time.Instant;
@Entity
@Table(name="log_product_deleted")
public class LogProductDeleted {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, unique = true)
    private Integer productId;

    private String productName;

    private Integer productPrice;

    private Instant moment;
}
