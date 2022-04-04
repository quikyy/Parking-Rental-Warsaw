package com.quikyy.Parking;
import com.quikyy.Order.Order;
import com.quikyy.Order.OrderDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ParkingService {

    private static final int mustAddThisOneDay = 1;
    private final ParkingSpotRepository parkingSpotRepository;

    public Optional<ParkingSpot> getFreeParkingSpot(OrderDTO orderDTO) {
        List<LocalDate> customerDateRange = orderDTO.getStartDate().datesUntil(orderDTO.getEndDate().plusDays(mustAddThisOneDay)).collect(Collectors.toList());
        List<ParkingSpot> parkingSpots = parkingSpotRepository.findAll();
        for (ParkingSpot spot : parkingSpots) {
            List<Order> orderList = spot.getOrderList();
            if (orderList.isEmpty()) {
                return Optional.of(spot);
            } else {
                for (Order order : orderList) {
                    List<LocalDate> orderDateRange = order.getStartDate().datesUntil(order.getEndDate().plusDays(mustAddThisOneDay)).collect(Collectors.toList());
                    boolean isTaken = orderDateRange.stream().anyMatch(localDate -> customerDateRange.contains(localDate));
                    if (isTaken) {
                        break;
                    } else {
                        return Optional.of(spot);
                    }
                }
            }
        }
        return Optional.empty();
    }
}

