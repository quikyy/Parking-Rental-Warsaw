package com.quikyy.Controller;

import com.quikyy.Order.Order;
import com.quikyy.Order.OrderRepository;
import com.quikyy.Order.OrderService;
import com.quikyy.Parking.ParkingService;
import com.quikyy.Parking.ParkingSpot;
import com.quikyy.Parking.ParkingSpotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDateTime;

@Controller
public class newReservationController {

    @Autowired
    private final OrderRepository orderRepository;

    @Autowired
    private final ParkingSpotRepository parkingSpotRepository;

    @Autowired
    private final OrderService orderService;

    @Autowired
    private final ParkingService parkingService;


    public newReservationController(OrderRepository orderRepository, ParkingSpotRepository parkingSpotRepository, OrderService orderService, ParkingService parkingService) {
        this.orderRepository = orderRepository;
        this.parkingSpotRepository = parkingSpotRepository;
        this.orderService = orderService;
        this.parkingService = parkingService;
    }


    @GetMapping("new")
    public String newReservationForm(Model model){
//        int freeParkingSpots = parkingSpotRepository.findAllByOrderIsNull().size();
//        model.addAttribute("freeParkingSpots", freeParkingSpots);
        model.addAttribute("newOrder", new Order());
        return "new";
    }

    @PostMapping("/make-reservation")
    public String makeNewReservation(@ModelAttribute Order order){
        order.setStartDate(orderService.startDateFormatter(order.getStartDateAsString()));
        order.setEndDate(orderService.startDateFormatter(order.getEndDateAsString()));
        ParkingSpot parkingSpot = parkingService.getParkingSpotTest2(order.getStartDate(), order.getEndDate());
        if(parkingSpot != null){
            order.setParkingSpot(parkingSpot);
            orderRepository.save(order);
            parkingSpot.getOrderList().add(order);
            parkingSpotRepository.save(parkingSpot);
            return "redirect:/new";
        }
        else {
            return "index";
        }
    }

//    @PostMapping("/make-reservation")
//    public String makeNewReservation(@ModelAttribute Order order){
//    ParkingSpot parkingSpot = parkingService.getParkingSpot();
//    return "redirect:/new";
//    }



}
