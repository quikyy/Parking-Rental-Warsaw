package com.quikyy.Controller;

import com.quikyy.Order.OrderDTO;
import com.quikyy.Order.OrderRepository;
import com.quikyy.Order.OrderService;
import com.quikyy.UTILS.CurrentWeather.CurrentWeather;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@AllArgsConstructor
@Controller
public class SummaryController {
    private final OrderService orderService;
    private final CurrentWeather currentWeather;
    private final OrderRepository orderRepository;

    @GetMapping("summary-reservation")
    public String redirect(){
       return "redirect:/";
    }

    @GetMapping("summary-reservation/{id}")
    public String summaryHTML(@ModelAttribute("confirmedOrder") OrderDTO orderDTO, Model model , @PathVariable(value = "id") String referenceNumber, HttpServletResponse response, HttpServletRequest request) {
        if(orderRepository.getOrderByReferenceNumberEquals(referenceNumber).isEmpty()){
            return "redirect:/";
        }
        model.addAttribute("currentWeather", currentWeather.getWeatherFromUserCookies(response, request));
        model.addAttribute("orderDTO", orderDTO);
        return "summary-reservation";
    }

}
