package com.quikyy.Order;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.quikyy.Parking.ParkingSpot;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Builder
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String firstName;
    private String lastName;
    private String telNum;
    private String carMark;
    private String carPlate;
    private String emailAddress;
    private BigDecimal price;
    private long days;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
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

}
