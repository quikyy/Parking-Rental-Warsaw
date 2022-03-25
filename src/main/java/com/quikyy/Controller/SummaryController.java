package com.quikyy.Controller;

import com.quikyy.Order.OrderDTO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class SummaryController {


    @GetMapping("summary")
    public String summaryHTML(@ModelAttribute OrderDTO orderDTO) {
        return "summary";
    }
}
