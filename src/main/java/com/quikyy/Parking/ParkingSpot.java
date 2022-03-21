package com.quikyy.Parking;

import com.quikyy.Customer.Customer;
import com.quikyy.Order.Order;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString
public class ParkingSpot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne
    private Customer customer;

    @OneToOne
    private Order order;

    public ParkingSpot() {
    }

    public ParkingSpot(Customer customer, Order order) {
        this.customer = customer;
        this.order = order;
    }
}
