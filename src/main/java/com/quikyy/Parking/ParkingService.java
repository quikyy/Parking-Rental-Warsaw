package com.quikyy.Parking;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParkingService {

    private final ParkingSpotRepository parkingSpotRepository;

    public ParkingService(ParkingSpotRepository parkingSpotRepository) {
        this.parkingSpotRepository = parkingSpotRepository;
    }


}
