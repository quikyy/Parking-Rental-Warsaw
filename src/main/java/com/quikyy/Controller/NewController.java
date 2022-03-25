package com.quikyy.Controller;
import com.quikyy.Order.OrderDTO;
import com.quikyy.Order.OrderRepository;
import com.quikyy.Order.OrderService;
import com.quikyy.Parking.ParkingService;
import com.quikyy.Parking.ParkingSpotRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@AllArgsConstructor
public class NewController {
    private final OrderService orderService;


    @GetMapping("new")
    public String newOrderHTML(Model model){
        model.addAttribute("newOrderDTO", new OrderDTO());
        return "new";
    }

    @PostMapping("new")
    public String makeNewReservation(@ModelAttribute OrderDTO orderDTO, Model model){
            if(orderService.manageOrder(orderDTO)){
                model.addAttribute("orderDTO", orderDTO);
                return "summary";
            }
            else {
                return "redirect:/new";
            }

    }



}
