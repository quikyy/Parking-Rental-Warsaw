package com.quikyy.Controller;
import com.quikyy.Order.Order;
import com.quikyy.Order.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@Controller
public class CheckController {

    private final OrderRepository orderRepository;

    @GetMapping("check")
    public String getAllOrders(Model model, String reference_number){
        model.addAttribute("orderList", orderRepository.findAll());
        if(reference_number != null){
            model.addAttribute("order", orderRepository.findOrderByReferenceNubmerEquals(reference_number));
        }
        else {
            model.addAttribute("order", new Order());
        }
        return "check";
    }


}
