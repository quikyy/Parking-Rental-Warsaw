package com.quikyy.Order;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.quikyy.Parking.ParkingSpot;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import java.time.LocalDate;

@Getter
@Setter
public class OrderDTO {
    private String firstName;
    private String lastName;
    private String telNum;
    private String carMark;
    private String carPlate;
    private LocalDate startDate;
    private LocalDate endDate;
    private ParkingSpot parkingSpot;
    private String startDateAsString;
    private String endDateAsString;
    private String referenceNubmer;

    public OrderDTO() {
    }

    public OrderDTO(String firstName, String lastName, String telNum, String carMark, String carPlate, LocalDate startDate, LocalDate endDate, ParkingSpot parkingSpot, String startDateAsString, String endDateAsString) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.telNum = telNum;
        this.carMark = carMark;
        this.carPlate = carPlate;
        this.startDate = startDate;
        this.endDate = endDate;
        this.parkingSpot = parkingSpot;
        this.startDateAsString = startDateAsString;
        this.endDateAsString = endDateAsString;
    }
}
