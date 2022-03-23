package com.quikyy;

import com.quikyy.Order.Order;
import com.quikyy.Order.OrderRepository;
import com.quikyy.Parking.ParkingService;
import com.quikyy.Parking.ParkingSpot;
import com.quikyy.Parking.ParkingSpotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@SpringBootApplication
public class ParkingRezerwacjeApplication implements CommandLineRunner {

    public static void main(String[] args){

        SpringApplication.run(ParkingRezerwacjeApplication.class, args);
    }

    @Autowired
    ParkingSpotRepository parkingSpotRepository;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    ParkingService parkingService;

    @Override
    public void run(String... args) throws Exception {
    miejscaParkingowe();
    order1();
    order2();
    order3();

//    parkingService.getParkingSpotTest();
//    testowy();

    }


    public void miejscaParkingowe(){
        for(int i = 1; i <= 10; i++){
            ParkingSpot parkingSpot = new ParkingSpot();
            parkingSpotRepository.save(parkingSpot);
        }
    }

    public void order1(){
        ParkingSpot parkingSpot = parkingSpotRepository.findById(2).get();
        String start1 = "2022-03-24";
        String end1 = "2022-03-28";
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        Order order1 = new Order("Szymon", "Paczuski", LocalDate.parse(start1, dateTimeFormatter), LocalDate.parse(end1, dateTimeFormatter), parkingSpot);
        orderRepository.save(order1);

        List<Order> orderList = parkingSpot.getOrderList();
        orderList.add(order1);

        parkingSpot.setOrderList(orderList);
        parkingSpotRepository.save(parkingSpot);
    }

    public void order2(){
        ParkingSpot parkingSpot = parkingSpotRepository.findById(2).get();
        String start1 = "2022-03-29";
        String end1 = "2022-03-31";
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        Order order1 = new Order("Marek", "Kowalczyk", LocalDate.parse(start1, dateTimeFormatter), LocalDate.parse(end1, dateTimeFormatter), parkingSpot);
        orderRepository.save(order1);

        List<Order> orderList = parkingSpot.getOrderList();
        orderList.add(order1);

        parkingSpot.setOrderList(orderList);
        parkingSpotRepository.save(parkingSpot);
    }

    public void order3(){
        ParkingSpot parkingSpot = parkingSpotRepository.findById(5).get();
        String start1 = "2022-03-29";
        String end1 = "2022-03-30";
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        Order order1 = new Order("Anna", "Topyk", LocalDate.parse(start1, dateTimeFormatter), LocalDate.parse(end1, dateTimeFormatter), parkingSpot);
        orderRepository.save(order1);

        List<Order> orderList = parkingSpot.getOrderList();
        orderList.add(order1);

        parkingSpot.setOrderList(orderList);
        parkingSpotRepository.save(parkingSpot);
    }

//    public void testowy(){
//        String start1 = "2022-03-20";
//        String end1 = "2022-03-31";
//        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//        LocalDate start = LocalDate.parse(start1, dateTimeFormatter);
//        LocalDate end = LocalDate.parse(end1, dateTimeFormatter);
//
//
//        Order order1 = new Order("Szymon", "Paczuski", start, end);
//
//        parkingService.getParkingSpotTest(start, end);
//    }


}
