package com.quikyy.Controller;
import com.quikyy.Order.Order;
import com.quikyy.Order.OrderDTO;
import com.quikyy.Order.OrderRepository;
import com.quikyy.UTILS.CurrentWeather.CurrentWeather;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@AllArgsConstructor
@Controller
public class CheckController {
    private final OrderRepository orderRepository;
    private final CurrentWeather currentWeather;

    @GetMapping("check-reservation")
    public String showCheckHTML(Model model, Model notfound, @NotNull String referenceNumber, HttpServletRequest request, HttpServletResponse response){
        currentWeather.getWeather(response, request, model);
        if(referenceNumber == null){
            model.addAttribute("orderDTO", new OrderDTO());
        }
        else {
            Order orderDTO = orderRepository.findOrderByReferenceNumberEquals(referenceNumber);
            if(orderDTO == null){
                notfound.addAttribute("notFound", referenceNumber);
                model.addAttribute("orderDTO", new OrderDTO());
            }
            else {
                model.addAttribute("orderDTO", orderDTO);
            }
        }
        return "check-reservation";
    }
}
