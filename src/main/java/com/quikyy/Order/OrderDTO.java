package com.quikyy.Order;


import com.quikyy.Parking.ParkingSpot;
import lombok.Getter;
import lombok.Setter;

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
    private String referenceNumber;

    public OrderDTO() {
    }

}
