package com.quikyy.Parking;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Setter
@Getter
@NoArgsConstructor
@Service
public class ParkingPrice {
    private final int pricePerNight = 50;
}
