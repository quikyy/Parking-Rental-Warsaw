package com.quikyy.Customer;

import com.quikyy.Order.Order;
import com.quikyy.Parking.ParkingSpot;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String firstName;
    private String lastName;
    private String telNumber;

    @OneToOne
    private ParkingSpot parkingSpot;

    @OneToOne
    private Order order;

    public Customer() {
    }

    public Customer(String firstName, String lastName, String telNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.telNumber = telNumber;
    }
}
