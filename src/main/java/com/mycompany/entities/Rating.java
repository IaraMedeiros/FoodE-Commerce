package com.mycompany.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.time.Instant;
import java.util.Objects;

@Entity
@Table(name="tb_rating")
public class Rating {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, unique = false)
    private Double estrelas;

    @Column(nullable = false, unique = false)
    private String comentario;
    @Column(nullable = false, unique = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy/MM/dd'T'HH:mm:ss'Z'", timezone = "GMT")
    private Instant momento;

    @ManyToOne
    @JoinColumn(name="cliente_id")
    private User user;

    public Rating(Integer id, Double estrelas, String comentario, Instant momento, User user) {
        this.id = id;
        this.estrelas = estrelas;
        this.comentario = comentario;
        this.momento = momento;
        this.user = user;
    }

    public Rating() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getEstrelas() {
        return estrelas;
    }

    public void setEstrelas(Double estrelas) {
        this.estrelas = estrelas;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String preco) {
        this.comentario = preco;
    }

    public Instant getMomento() {
        return momento;
    }

    public void setMomento(Instant momento) {
        this.momento = momento;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rating rating = (Rating) o;
        return Objects.equals(id, rating.id) && Objects.equals(estrelas, rating.estrelas) && Objects.equals(comentario, rating.comentario) && Objects.equals(momento, rating.momento) && Objects.equals(user, rating.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, estrelas, comentario, momento, user);
    }

    @Override
    public String toString() {
        return "Rating{" +
                "id=" + id +
                ", estrelas=" + estrelas +
                ", preco='" + comentario + '\'' +
                ", momento=" + momento +
                ", user=" + user +
                '}';
    }
}
