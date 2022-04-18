package com.quikyy.Controller;

import com.quikyy.Parking.ParkingSpotRepository;
import com.quikyy.UTILS.CurrentWeather.CurrentWeather;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@AllArgsConstructor
@Controller
public class HomeController{
    private final ParkingSpotRepository parkingSpotRepository;
    private final CurrentWeather currentWeather;


    @GetMapping("/")
    public String test(Model model, HttpServletResponse response, HttpServletRequest request){
        model.addAttribute("currentWeather", currentWeather.getWeatherFromUserCookies(response, request));
        return "index";
    }
}
