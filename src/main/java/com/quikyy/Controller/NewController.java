package com.quikyy.Controller;
import com.quikyy.Order.OrderDTO;
import com.quikyy.Order.OrderService;
import com.quikyy.UTILS.CurrentWeather;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@AllArgsConstructor
public class NewController {
    private final OrderService orderService;
    private final CurrentWeather currentWeather;

    @GetMapping("new-reservation")
    public String newOrderHTML(@ModelAttribute("rejectedOrder") OrderDTO rejectedOrder, Model model) {
        model.addAttribute("currentWeather", currentWeather.getCurrentWeather());
        if(rejectedOrder.getFirstName() == null){
            model.addAttribute("newOrderDTO", new OrderDTO());
        }
        else {
            rejectedOrder.setStartDateAsString("");
            rejectedOrder.setEndDateAsString("");
            model.addAttribute("newOrderDTO", rejectedOrder);
        }
       return "new-reservation";
    }

    @PostMapping("new-reservation")
    public String makeNewReservation(@ModelAttribute OrderDTO orderDTO, RedirectAttributes redirectAttributes) {
        if (orderService.manageOrder(orderDTO)) {
            redirectAttributes.addFlashAttribute("confirmedOrder", orderDTO);
            return "redirect:summary-reservation/" + orderDTO.getReferenceNumber();
        } else {
            redirectAttributes.addFlashAttribute("rejectedOrder", orderDTO);
            return "redirect:new-reservation";
        }
    }

}
