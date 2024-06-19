package com.mycompany.entities.logs;

import com.mycompany.entities.Product;
import jakarta.persistence.*;

import java.time.Instant;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
@Entity
@Table(name="log_order_canceled")
public class LogOrderCanceled {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer id;

        @Column(nullable = false, unique = true)
        private Integer orderId;

        private Date moment;

}
