package com.quikyy.Controller;
import com.quikyy.Order.OrderDTO;
import com.quikyy.Order.OrderService;
import com.quikyy.UTILS.CurrentWeather.CurrentWeather;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Controller
@AllArgsConstructor
public class NewController {
    private final OrderService orderService;
    private final CurrentWeather currentWeather;

    @GetMapping("new-reservation")
    public String newOrderHTML(@ModelAttribute("rejectedOrder") OrderDTO rejectedOrder, Model model, HttpServletRequest request, HttpServletResponse response) {
        model.addAttribute("currentWeather", currentWeather.getWeatherFromUserCookies(response, request));

        if(rejectedOrder.getFirstName() == null){
            model.addAttribute("newOrderDTO", new OrderDTO());
        }
        else {
            model.addAttribute("newOrderDTO", rejectedOrder);
        }
       return "new-reservation";
    }

    @PostMapping("new-reservation")
    public String makeNewReservation(@ModelAttribute OrderDTO orderDTO, RedirectAttributes redirectAttributes) {

        if (orderService.manageOrder(orderDTO)) {
            redirectAttributes.addFlashAttribute("confirmedOrder", orderDTO);
            return "redirect:summary-reservation/" + orderDTO.getReferenceNumber();
        }

        else {
            redirectAttributes.addFlashAttribute("rejectedOrder", orderDTO);
            return "redirect:new-reservation";
        }
    }

}
