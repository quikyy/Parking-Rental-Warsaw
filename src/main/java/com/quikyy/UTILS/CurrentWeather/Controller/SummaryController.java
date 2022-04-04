package com.quikyy.UTILS.CurrentWeather.Controller;

import com.quikyy.Order.OrderDTO;
import com.quikyy.Order.OrderRepository;
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


    @GetMapping("summary-reservation/{id}")
    public String summaryHTML(@ModelAttribute("confirmedOrder") OrderDTO orderDTO, Model model ,@PathVariable(value = "id") String referenceNumber) {
        if(orderRepository.findOrderByReferenceNumberEquals(referenceNumber) != null){
            model.addAttribute("orderDTO", orderDTO);
            return "summary-reservation";
        }
        else  {
            return "TBD";
        }
    }

}
