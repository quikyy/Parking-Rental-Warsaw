package com.quikyy.Controller;

import com.quikyy.Parking.ParkingSpot;
import com.quikyy.Parking.ParkingSpotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class viewController {
    @Autowired
    private final ParkingSpotRepository parkingSpotRepository;

    public viewController(ParkingSpotRepository parkingSpotRepository) {
        this.parkingSpotRepository = parkingSpotRepository;
    }


    @GetMapping("check")
    public String checkFreeSpots(Model model){
        List<ParkingSpot> parkingSpots = parkingSpotRepository.findAllByCustomerIsNullAndOrderIsNull();
        model.addAttribute("parkingSpots", parkingSpots);
        return "check";
    }

    @GetMapping("new")
    public String makeNewReservation(Model model){

        return "new";
    }

}
