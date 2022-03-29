package com.quikyy.Order;
import com.quikyy.Parking.ParkingService;
import com.quikyy.Parking.ParkingSpot;
import com.quikyy.Parking.ParkingSpotRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final ParkingSpotRepository parkingSpotRepository;
    private final ParkingService parkingService;

    public OrderService(OrderRepository orderRepository, ParkingSpotRepository parkingSpotRepository, ParkingService parkingService) {
        this.orderRepository = orderRepository;
        this.parkingSpotRepository = parkingSpotRepository;
        this.parkingService = parkingService;
    }

//    1. Sprawdzić czy daty są poprawne (nie są w złej kolejności itp)
    public boolean validateStartEndDate(OrderDTO orderDTO){
        orderDTO.setStartDate(formatStartEndDate(orderDTO.getStartDateAsString()));
        orderDTO.setEndDate(formatStartEndDate(orderDTO.getEndDateAsString()));
        if(orderDTO.getStartDate().isBefore(orderDTO.getEndDate()) || orderDTO.getStartDate().isEqual(orderDTO.getEndDate())){
            return true;
        }
        else {
            return false;
        }
    }
//    2. Znajdz wolne miejsce dla tego zamówienia i jeżeli jest, to zapisz zamówienie.
    public boolean manageOrder(OrderDTO orderDTO){
        if(validateStartEndDate(orderDTO)) {
            ParkingSpot spot = parkingService.getFreeParkingSpot(orderDTO);
            if(spot != null){
                orderDTO.setReferenceNumber(generateRefernceNumer());
                Order order = new Order();
                order.setFirstName(orderDTO.getFirstName());
                order.setLastName(orderDTO.getLastName());
                order.setTelNum(orderDTO.getTelNum());
                order.setCarMark(orderDTO.getCarMark());
                order.setCarPlate(orderDTO.getCarPlate());
                order.setStartDate(orderDTO.getStartDate());
                order.setEndDate(orderDTO.getEndDate());
                order.setParkingSpot(spot);
                order.setReferenceNumber(orderDTO.getReferenceNumber());
                orderRepository.save(order);

                spot.getOrderList().add(order);
                parkingSpotRepository.save(spot);
                return true;
            }
        }
        else {
            return false;
        }
        return false;
    }

    public LocalDate formatStartEndDate(String data) {
        data = data.substring(0, 10);
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse(data, dateTimeFormatter);
        return localDate;
    }
    public String generateRefernceNumer(){
        final int refrence_number_lenght = 8;
        return RandomStringUtils.random(refrence_number_lenght, "0123456789qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM");
    }
}
