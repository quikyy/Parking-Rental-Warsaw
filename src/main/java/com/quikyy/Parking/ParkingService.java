package com.quikyy.Parking;
import com.quikyy.Order.Order;
import com.quikyy.Order.OrderDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
@AllArgsConstructor
public class ParkingService {

    private final ParkingSpotRepository parkingSpotRepository;

    @Transactional
    public Optional <ParkingSpot> getFreeParkingSpot(OrderDTO orderDTO) {
        long hoursUntillStartEndCustomer = orderDTO.getStartDate().until(orderDTO.getEndDate(), ChronoUnit.HOURS);

        List <LocalDateTime> customerDataRange = new ArrayList<>();
        for(int i = 0; i <= hoursUntillStartEndCustomer; i++){
            customerDataRange.add(orderDTO.getStartDate().plusHours(i));
        }

        List<ParkingSpot> parkingSpots = parkingSpotRepository.findAll();
        for(ParkingSpot parkingSpot : parkingSpots){
            List<Order> orderList = parkingSpot.getOrderList();
            if(orderList.isEmpty()) {
                return Optional.of(parkingSpot);
            }

            else {
                List<LocalDateTime> orderDateRange = new ArrayList<>();
                for(Order order: orderList){
                    long hoursUntilStartEndOrder = order.getStartDate().until(order.getEndDate(), ChronoUnit.HOURS);
                    for(int i = 0; i <= hoursUntilStartEndOrder; i++){
                        orderDateRange.add(order.getStartDate().plusHours(i));
                    }

                    for(LocalDateTime localDateTime : orderDateRange){
                        System.out.println(localDateTime);
                    }

                    boolean isTaken = orderDateRange.stream().anyMatch(localDateTime -> customerDataRange.contains(localDateTime));

                    if(!isTaken){
                        return Optional.of(parkingSpot);
                    }
                }
            }
        }
        return Optional.empty();
    }
}
