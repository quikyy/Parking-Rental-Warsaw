package com.quikyy.Controller;
import com.quikyy.Order.Order;
import com.quikyy.Order.OrderDTO;
import com.quikyy.Order.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@Controller
public class CheckController {
    private final OrderRepository orderRepository;

    @GetMapping("check-reservation")
    public String showCheckHTML(Model model, String referenceNumber){
        System.out.println(referenceNumber);
        if(referenceNumber == null){
            model.addAttribute("orderDTO", new OrderDTO());
        }
        else {
            Order orderDTO = orderRepository.findOrderByReferenceNumberEquals(referenceNumber);
            if(orderDTO == null){
                model.addAttribute("orderDTO", new OrderDTO());
            }
            else {
                model.addAttribute("orderDTO", orderDTO);
            }
        }
        return "check-reservation";
    }
}
