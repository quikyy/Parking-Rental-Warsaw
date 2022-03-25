package com.quikyy.Order;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Integer> {

    Order findOrderByFirstNameEquals(String keyword);
    Order findOrderByReferenceNubmerEquals(String reference_number);
}
