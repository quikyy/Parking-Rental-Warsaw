package com.quikyy.Controller;

import com.quikyy.Parking.ParkingSpotRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@AllArgsConstructor
@Controller
public class HomeController {
    private final ParkingSpotRepository parkingSpotRepository;
}
