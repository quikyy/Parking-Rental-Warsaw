package com.quikyy.Order;
import com.quikyy.UTILS.MailSender.MailSender;
import com.quikyy.Parking.ParkingPrice;
import com.quikyy.Parking.ParkingService;
import com.quikyy.Parking.ParkingSpot;
import com.quikyy.Parking.ParkingSpotRepository;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import static java.time.temporal.ChronoUnit.DAYS;

@Service
@AllArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final ParkingSpotRepository parkingSpotRepository;
    private final ParkingService parkingService;
    private final MailSender mailSender;
    private final ParkingPrice parkingPrice;


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

    public String generateRefernceNumber(){
        final int referenceNumberLength = 8;
        final String chars = "0123456789qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM";
        return RandomStringUtils.random(referenceNumberLength, chars);
    }

    public BigDecimal calculatePrice(OrderDTO orderDTO){
        long days = DAYS.between(orderDTO.getStartDate(), orderDTO.getEndDate());
        orderDTO.setDays(days);
        return new BigDecimal(days * parkingPrice.getPricePerNight());
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

    public boolean manageOrder(OrderDTO orderDTO){
        if(validateStartEndDate(orderDTO)) {
            Optional <ParkingSpot> spot = parkingService.getFreeParkingSpot(orderDTO);
            if(spot.isPresent()){
                orderDTO.setReferenceNumber(generateRefernceNumber());
                orderDTO.setParkingSpot(spot.get());
                orderDTO.setPrice(calculatePrice(orderDTO));

                Order order = new Order();
                order.setFirstName(orderDTO.getFirstName());
                order.setLastName(orderDTO.getLastName());
                order.setTelNum(orderDTO.getTelNum());
                order.setCarMark(orderDTO.getCarMark());
                order.setCarPlate(orderDTO.getCarPlate());
                order.setEmailAddress(orderDTO.getEmailAddress());
                order.setStartDate(orderDTO.getStartDate());
                order.setEndDate(orderDTO.getEndDate());
                order.setParkingSpot(spot.get());
                order.setReferenceNumber(orderDTO.getReferenceNumber());
                order.setDays(orderDTO.getDays());
                order.setPrice(orderDTO.getPrice());

                orderRepository.save(order);

                spot.get().getOrderList().add(order);
                parkingSpotRepository.save(spot.get());

//                Wysyłanie maila z potwierdzeniem. Aktualnie: długo trwa, trzeba poprawić wiadomość która jest wysyłana.
//                sendConfirmationMail(order);
                return true;
            }
        }
        else {
            return false;
        }
        return false;
    }
}
