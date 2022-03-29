package com.quikyy.Order;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Integer> {
    Order findOrderByReferenceNumberEquals(String keyword);
}
