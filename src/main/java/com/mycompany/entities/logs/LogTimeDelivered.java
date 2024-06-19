package com.mycompany.entities.logs;

import jakarta.persistence.*;

import java.util.Date;
@Entity
@Table(name="log_time_delivered")
public class LogTimeDelivered {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, unique = true)
    private Integer orderId;

    private Date moment;

}
