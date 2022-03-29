package com.quikyy.Order;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.quikyy.Parking.ParkingSpot;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@ToString
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String firstName;
    private String lastName;
    private String telNum;
    private String carMark;
    private String carPlate;
    private LocalDate startDate;
    private LocalDate endDate;
    private String referenceNumber;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "spot_id")
    private ParkingSpot parkingSpot;

    @JsonIgnore
    @Transient
    private String startDateAsString;
    @JsonIgnore
    @Transient
    private String endDateAsString;

    public Order() {
    }

    public Order(String firstName, String lastName, LocalDate startDate, LocalDate endDate, ParkingSpot parkingSpot) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.parkingSpot = parkingSpot;
    }
}
