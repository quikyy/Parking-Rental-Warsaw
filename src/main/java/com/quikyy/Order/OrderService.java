package com.quikyy.Order;
import com.quikyy.MailSender.MailSender;
import com.quikyy.Parking.ParkingService;
import com.quikyy.Parking.ParkingSpot;
import com.quikyy.Parking.ParkingSpotRepository;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.aspectj.weaver.ast.Or;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Service
@AllArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final ParkingSpotRepository parkingSpotRepository;
    private final ParkingService parkingService;
    private final MailSender mailSender;

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
    public boolean manageOrder(OrderDTO orderDTO){
        if(validateStartEndDate(orderDTO)) {
            ParkingSpot spot = parkingService.getFreeParkingSpot(orderDTO);
            if(spot != null){
                orderDTO.setReferenceNumber(generateRefernceNumer());
                orderDTO.setParkingSpot(spot);

                Order order = new Order();
                order.setFirstName(orderDTO.getFirstName());
                order.setLastName(orderDTO.getLastName());
                order.setTelNum(orderDTO.getTelNum());
                order.setCarMark(orderDTO.getCarMark());
                order.setCarPlate(orderDTO.getCarPlate());
                order.setEmailAddress(orderDTO.getEmailAddress());
                order.setStartDate(orderDTO.getStartDate());
                order.setEndDate(orderDTO.getEndDate());
                order.setParkingSpot(spot);
                order.setReferenceNumber(orderDTO.getReferenceNumber());
                orderRepository.save(order);

                spot.getOrderList().add(order);
                parkingSpotRepository.save(spot);

                sendConfirmationMail(order);
                return true;
            }
        }
        else {
            return false;
        }
        return false;
    }
    public void sendConfirmationMail(Order order){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(order.getEmailAddress());
        message.setSubject("Potwierdzenie rezerwacji " + order.getReferenceNumber());
        message.setText("Dzień dobry! \n" +
                "Potwierdzenie rezerwacji na naszym parkingu, poniżej przesyłamy wszystkie szczegóły: \n" +
                "Imię i nazwsiko: " + order.getFirstName() + " " + order.getLastName() + "\n" +
                "Data rozpoczęcia: " + order.getStartDate() + "\n" +
                "Data zakończenia :" + order.getLastName() + "\n " +
                "etc....");
        mailSender.getJavaMailSender().send(message);
    }
}
