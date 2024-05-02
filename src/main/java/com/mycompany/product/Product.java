package com.mycompany.product;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mycompany.OrderItem.OrderItem;
import com.mycompany.category.Category;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name="tb_product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = true, unique = false)
    private String description;

    @Column(nullable = false, unique = false)
    private Double price;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="category_id")
    private Category category;
    @JsonIgnore
    @OneToMany(mappedBy = "id.product")
    private Set<OrderItem> items = new HashSet<>();


    public Product() {
    }

    public Product(Integer id, String name, String description, Double price, Category category) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.category = category;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
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
        Product product = (Product) o;
        return Objects.equals(id, product.id) && Objects.equals(name, product.name);
    }



    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
