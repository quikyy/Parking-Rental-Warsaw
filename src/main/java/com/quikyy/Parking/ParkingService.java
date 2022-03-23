package com.quikyy.Parking;

import com.quikyy.Order.Order;
import com.quikyy.Order.OrderRepository;
import com.quikyy.Order.OrderService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class ParkingService {

    private final ParkingSpotRepository parkingSpotRepository;
    private final OrderRepository orderRepository;
    private  final OrderService orderService;
    private final Random random = new Random();

    public ParkingService(ParkingSpotRepository parkingSpotRepository, OrderRepository orderRepository, OrderService orderService) {
        this.parkingSpotRepository = parkingSpotRepository;
        this.orderRepository = orderRepository;
        this.orderService = orderService;
    }

    public ParkingSpot getParkingSpot(){
        List <ParkingSpot> parkingSpots = parkingSpotRepository.findAll();
        int min = 1;
        int max = parkingSpots.size();
        return parkingSpots.get(random.nextInt(max - min + 1));
    }

    public ParkingSpot getParkingSpotTest2(LocalDate start, LocalDate end){
        List <LocalDate> listaCustomera = start.datesUntil(end.plusDays(1)).collect(Collectors.toList());
        List <ParkingSpot> parkingSpots = parkingSpotRepository.findAll();
        for(ParkingSpot spot : parkingSpots){
            List <Order> orderList = spot.getOrderList();
            if(orderList.isEmpty()){
                System.out.println("ID: " + spot.getId() + " jest pusta. Przypisuję");
                return spot;
            }
            else {
                for(Order order : orderList) {
                    List <LocalDate> zarezerowaneDaty = order.getStartDate().datesUntil(order.getEndDate().plusDays(1)).collect(Collectors.toList());
                    boolean isTaken = zarezerowaneDaty.stream().anyMatch(localDate -> listaCustomera.contains(localDate));
                    if(isTaken == true){
                        System.out.println("ID: " + spot.getId() + " jest zajęty w tym okresie.");
                        break;
                    }
                    else {
                        System.out.println("ID: " + spot.getId() + "jest wolny w tym okresie. Przypisuję :)");
                        return spot;
                    }
                }
            }
            }

        return null;
    }
//    public void getParkingSpotTest(LocalDate start, LocalDate end){
//        List <LocalDate> listaCustomera = start.datesUntil(end.plusDays(1)).collect(Collectors.toList());
//        List <ParkingSpot> parkingSpots = parkingSpotRepository.findAll();
//
//
//        for(ParkingSpot spot : parkingSpots){
//            List <Order> orderList = spot.getOrderList();
//            if(orderList.isEmpty()){
//                System.out.println("Przypisuję miejsce " + spot.getId());
//            }
//
//            for(Order order : orderList) {
//                List <LocalDate> zarezerowaneDaty = order.getStartDate().datesUntil(order.getEndDate().plusDays(1)).collect(Collectors.toList());
//                boolean var = listaCustomera.stream().anyMatch(localDate -> zarezerowaneDaty.contains(localDate));
//                if(var == true){
//                    System.out.println("Spot o ID " + spot.getId() + " jest zajęty w okresie od " + order.getStartDate() + " do " + order.getEndDate());
//                    continue;
//                }
//                else {
//                    System.out.println("Spot o ID " + spot.getId() + " jest wolny w okresie od " + order.getStartDate() + " do " + order.getEndDate());
//                }
//            }
//        }
//    }
//    public void getParkingSpotTest(){
//        List <ParkingSpot> parkingSpots = parkingSpotRepository.findAll();
//        for(ParkingSpot spot : parkingSpots){
//            List <Order> orderList = spot.getOrderList();
//            for(Order order : orderList) {
//                List <LocalDate> dates = order.getStartDate().datesUntil(order.getEndDate().plusDays(1)).collect(Collectors.toList());
//                for(LocalDate date : dates) {
//                    System.out.println(spot.getId() + " | Pomiędzy: " + date);
//                }
//
//            }
//        }
//
//    }

}

