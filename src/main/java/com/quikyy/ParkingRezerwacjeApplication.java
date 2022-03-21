package com.quikyy;

import com.quikyy.Parking.ParkingService;
import com.quikyy.Parking.ParkingSpot;
import com.quikyy.Parking.ParkingSpotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
public class ParkingRezerwacjeApplication implements CommandLineRunner {

    public static void main(String[] args){

        SpringApplication.run(ParkingRezerwacjeApplication.class, args);
    }

    @Autowired
    ParkingSpotRepository parkingSpotRepository;

    @Autowired
    ParkingService parkingService;

    @Override
    public void run(String... args) throws Exception {
    miejscaParkingowe();

    }

    public void miejscaParkingowe(){
        for(int i = 1; i <= 10; i++){
            ParkingSpot parkingSpot = new ParkingSpot();
            parkingSpotRepository.save(parkingSpot);
        }
    }

}
