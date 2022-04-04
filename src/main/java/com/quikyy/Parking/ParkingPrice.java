package com.quikyy.Parking;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Setter
@Getter
@Service
public class ParkingPrice {
    private final int pricePerNight = 50;
}
