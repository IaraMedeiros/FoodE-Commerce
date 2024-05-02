package com.mycompany.order;

import com.mycompany.OrderItem.OrderItem;
import com.mycompany.OrderItem.OrderItemRepository;
import com.mycompany.enums.OrderStatus;
import com.mycompany.product.Product;
import com.mycompany.product.ProductRepository;
import com.mycompany.user.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.io.IOException;
import java.time.Instant;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class OrderRepositoryTests {

    @Autowired
    private OrderRepository orderRepo;

    @Autowired
    private OrderItemRepository orderItemRepo;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testAddNew() throws IOException {

        Optional<Product> optionalProduct = productRepository.findById(4);
        Optional<Product> optionalProduct2 = productRepository.findById(5);
        Product product = optionalProduct.get();
        Product product2 = optionalProduct2.get();

        Order order = new Order(null);
        Integer quantity = 1;
        Integer quantity2 = 2;

        orderRepo.save(order);

        OrderItem orderItem = new OrderItem(order,product,quantity2,product.getPrice()*quantity2);
        OrderItem orderItem2 = new OrderItem(order,product2,quantity,product2.getPrice()*quantity);

        System.out.println(product.getPrice()*quantity2);
        System.out.println(product2.getPrice()*quantity);

        orderItemRepo.save(orderItem);
        orderItemRepo.save(orderItem2);

        order.setMoment(Instant.now());
        order.setOrderStatus(OrderStatus.PAID);
        order.setCostumer(userRepository.findById(1).get());
        order.getItems().add(orderItem);
        order.getItems().add(orderItem2);
        order.setValor(order.getSubTotal());

        Order savedOrder = orderRepo.save(order);

        Order retrievedOrder = orderRepo.findById(savedOrder.getId()).orElse(null);


        // Verificar se o produto foi salvo com sucesso
        assertThat(savedOrder).isNotNull();
        assertThat(savedOrder.getId()).isNotNull();
        assertThat(savedOrder.getId()).isGreaterThan(0);
        assertThat(savedOrder.getItems().size()).isEqualTo(2);
    }

}
