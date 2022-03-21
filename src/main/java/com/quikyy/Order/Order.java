package com.quikyy.Order;

import com.quikyy.Customer.Customer;
import com.quikyy.Parking.ParkingSpot;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@ToString
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String carMark;
    private String carPlate;
    private LocalDateTime startDate;
    private LocalDateTime endDate;

    @OneToOne
    private ParkingSpot parkingSpot;

    @OneToOne
    private Customer customer;

    public Order() {
    }

    public Order(String carMark, String carPlate, LocalDateTime startDate, LocalDateTime endDate, ParkingSpot parkingSpot, Customer customer) {
        this.carMark = carMark;
        this.carPlate = carPlate;
        this.startDate = startDate;
        this.endDate = endDate;
        this.parkingSpot = parkingSpot;
        this.customer = customer;
    }
}
