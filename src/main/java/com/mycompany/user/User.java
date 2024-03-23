package com.mycompany.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mycompany.product.Product;
import com.mycompany.rating.Rating;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, unique = true, length = 45)
    private String email;

    @Column(nullable = false, unique = true, length = 11)
    private String phone;

    @Column(length = 50, nullable = false)
    private String name;

    @Column(length = 11, nullable = false)
    private String CPF;
    @JsonIgnore
    @OneToMany(mappedBy="user")
    private Set<Rating> ratings = new HashSet<>();

    public User(Integer id, String email, String phone, String name, String CPF) {
        this.id = id;
        this.email = email;
        this.phone = phone;
        this.name = name;
        this.CPF = CPF;
    }

    public User() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCPF() {
        return CPF;
    }

    public void setCPF(String CPF) {
        this.CPF = CPF;
    }

    public Set<Rating> getRatings() {
        return ratings;
    }

    public void setRatings(Set<Rating> ratings) {
        this.ratings = ratings;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) && Objects.equals(CPF, user.CPF);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, CPF);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", name='" + name + '\'' +
                ", CPF='" + CPF + '\'' +
                '}';
    }
}
