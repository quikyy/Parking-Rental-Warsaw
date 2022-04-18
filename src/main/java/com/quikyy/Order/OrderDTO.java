package com.quikyy.Order;
import com.quikyy.Parking.ParkingSpot;
import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
public class OrderDTO {
    private String firstName;
    private String lastName;
    private String telNum;
    private String carMark;
    private String carPlate;
    private String emailAddress;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private BigDecimal price;
    private Long days;
    private ParkingSpot parkingSpot;
    private String startDateAsString;
    private String endDateAsString;
    private String referenceNumber;
    public OrderDTO() {
    }

}
