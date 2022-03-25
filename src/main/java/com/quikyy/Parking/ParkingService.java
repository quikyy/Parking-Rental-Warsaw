package com.quikyy.Parking;

import com.quikyy.Order.Order;
import com.quikyy.Order.OrderDTO;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ParkingService {

    private final ParkingSpotRepository parkingSpotRepository;
    public ParkingService(ParkingSpotRepository parkingSpotRepository) {
        this.parkingSpotRepository = parkingSpotRepository;
    }


    public ParkingSpot getFreeParkingSpot(OrderDTO orderDTO) {
        List<LocalDate> customerDateRange = orderDTO.getStartDate().datesUntil(orderDTO.getEndDate().plusDays(1)).collect(Collectors.toList());
        List<ParkingSpot> parkingSpots = parkingSpotRepository.findAll();
        for (ParkingSpot spot : parkingSpots) {
            List<Order> orderList = spot.getOrderList();
            if (orderList.isEmpty()) {
                System.out.println("ID: " + spot.getId() + " jest pusta. Przypisuję");
                return spot;
            } else {
                for (Order order : orderList) {
                    List<LocalDate> orderDateRange = order.getStartDate().datesUntil(order.getEndDate().plusDays(1)).collect(Collectors.toList());
                    boolean isTaken = orderDateRange.stream().anyMatch(localDate -> customerDateRange.contains(localDate));
                    if (isTaken) {
                        System.out.println("ID: " + spot.getId() + " jest zajęty w tym okresie.");
                        break;
                    } else {
                        System.out.println("ID: " + spot.getId() + "jest wolny w tym okresie. Przypisuję :)");
                        return spot;
                    }
                }
            }
        }
        return null;
    }
}

