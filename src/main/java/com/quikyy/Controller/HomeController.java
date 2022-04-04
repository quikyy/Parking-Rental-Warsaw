package com.quikyy.Controller;

import com.quikyy.Parking.ParkingSpotRepository;
import com.quikyy.UTILS.CurrentWeather.CurrentWeather;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@AllArgsConstructor
@Controller
public class HomeController {
    private final ParkingSpotRepository parkingSpotRepository;
    private final CurrentWeather currentWeather;

    @GetMapping("/")
    public String test(Model model){
        model.addAttribute("currentWeather", currentWeather.getCurrentWeather());
        return "index";
    }
}
