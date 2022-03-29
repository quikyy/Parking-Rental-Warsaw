package com.quikyy;
import com.quikyy.Order.Order;
import com.quikyy.Order.OrderRepository;
import com.quikyy.Order.OrderService;
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
public class ParkingApplication implements CommandLineRunner {

    public static void main(String[] args){

        SpringApplication.run(ParkingApplication.class, args);
    }


    @Autowired
    ParkingSpotRepository parkingSpotRepository;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    ParkingService parkingService;

    @Autowired
    OrderService orderService;


    @Override
    public void run(String... args) throws Exception {
        miejscaParkingowe();
    }


    public void miejscaParkingowe(){
        for(int i = 0; i < 1; i++){
            ParkingSpot parkingSpot = new ParkingSpot();
            parkingSpotRepository.save(parkingSpot);
        }
    }
    public void order1(){
        ParkingSpot parkingSpot = parkingSpotRepository.findById(1).get();
        String start1 = "2022-04-01";
        String end1 = "2022-04-25";
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        Order order1 = new Order("Szymon", "Paczuski", LocalDate.parse(start1, dateTimeFormatter), LocalDate.parse(end1, dateTimeFormatter), parkingSpot);
        order1.setReferenceNumber(orderService.generateRefernceNumer());
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
        order1.setReferenceNumber(orderService.generateRefernceNumer());
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
        order1.setReferenceNumber(orderService.generateRefernceNumer());
        orderRepository.save(order1);

        List<Order> orderList = parkingSpot.getOrderList();
        orderList.add(order1);

        parkingSpot.setOrderList(orderList);
        parkingSpotRepository.save(parkingSpot);
    }
   }
