package com.mycompany;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.io.Serializable;
import java.util.Objects;
import com.mycompany.order.Order;
import com.mycompany.product.Product;
@Embeddable
public class OrderItemPK implements Serializable {
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "order_id")

    private Order order;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    public OrderItemPK(Order order, Product product) {
        this.order = order;
        this.product = product;
    }

    public OrderItemPK() {

    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

}
