package com.quikyy.Order;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface OrderRepository extends JpaRepository<Order, Integer> {
    Order findOrderByReferenceNumberEquals(String keyword);

    @Query("select o from Order o where o.emailAddress = ?1")
    Order findByEmailAdress(String email);
}
