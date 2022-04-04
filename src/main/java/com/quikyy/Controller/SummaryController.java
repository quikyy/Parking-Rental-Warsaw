package com.quikyy.Controller;

import com.quikyy.Order.OrderDTO;
import com.quikyy.Order.OrderRepository;
import com.quikyy.UTILS.CurrentWeather.CurrentWeather;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

@AllArgsConstructor
@Controller
public class SummaryController {
    private final OrderRepository orderRepository;
    private final CurrentWeather currentWeather;

    @GetMapping("summary-reservation/{id}")
    public String summaryHTML(@ModelAttribute("confirmedOrder") OrderDTO orderDTO, Model model ,@PathVariable(value = "id") String referenceNumber) {
        model.addAttribute("currentWeather", currentWeather.getCurrentWeather());
        if(orderRepository.findOrderByReferenceNumberEquals(referenceNumber) != null){
            model.addAttribute("orderDTO", orderDTO);
            return "summary-reservation";
        }
        else  {
            return "TBD";
        }
    }

}
