package com.project.gamelibrary.domain;

import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.FetchType.*;

@Entity
@Table(name="orders")
@Getter
public class Order {
    @Id @GeneratedValue
    @Column(name = "order_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "order")
    private List<OrderItem> orderItems = new ArrayList<OrderItem>();

    @OneToOne
    @JoinColumn(name = "delivery_id")
    private Delivery delivery;

    private LocalDateTime orderDate;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    public static class Builder {
        private final User user;
        private final List<OrderItem> orderItems;
        private final Delivery delivery;
        private final LocalDateTime orderDate;
        private final OrderStatus orderStatus;

        public Builder(User user, List<OrderItem> orderItems, Delivery delivery, LocalDateTime orderDate, OrderStatus orderStatus) {
            this.user = user;
            this.orderItems = orderItems;
            this.delivery = delivery;
            this.orderDate = orderDate;
            this.orderStatus = orderStatus;
        }
        public Order build() { return new Order(this);}
    }
    protected Order() {};

    private Order(Builder builder) {
        this.user = builder.user;
        this.orderItems = builder.orderItems;
        this.delivery = builder.delivery;
        this.orderDate = builder.orderDate;
        this.orderStatus = builder.orderStatus;
    }
    //== 연관 관계 메서드 ==//
    public void setUser(User user) {
        this.user = user;
        user.getOrders().add(this);
    }
}
