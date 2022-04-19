package com.quikyy.Order;
import com.quikyy.Parking.ParkingPrice;
import com.quikyy.Parking.ParkingService;
import com.quikyy.Parking.ParkingSpot;
import com.quikyy.Parking.ParkingSpotRepository;
import com.quikyy.UTILS.MailSender.MailSenderService;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import static java.time.temporal.ChronoUnit.DAYS;

@Service
@AllArgsConstructor
@Transactional
public class OrderService {

    private final OrderRepository orderRepository;
    private final ParkingSpotRepository parkingSpotRepository;
    private final ParkingService parkingService;
    private final MailSenderService mailSenderService;
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

    public LocalDateTime formatStartEndDate(String data) {
        return LocalDateTime.parse(data, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }

    public String generateReferenceNumber(){
        final int referenceNumberLength = 8;
        final String chars = "0123456789QWERTYUIOPASDFGHJKLZXCVBNM";
        return RandomStringUtils.random(referenceNumberLength, chars);
    }

    public BigDecimal calculatePrice(OrderDTO orderDTO){
        long days = DAYS.between(orderDTO.getStartDate(), orderDTO.getEndDate());
        orderDTO.setDays(days);
        return new BigDecimal(days * parkingPrice.getPricePerNight());
    }

    public Order buildOrder(OrderDTO orderDTO, ParkingSpot spot){
        return Order.builder()
                .firstName(orderDTO.getFirstName())
                .lastName(orderDTO.getLastName())
                .telNum(orderDTO.getTelNum())
                .carMark(orderDTO.getCarMark())
                .carPlate(orderDTO.getCarPlate())
                .emailAddress(orderDTO.getEmailAddress())
                .startDate(orderDTO.getStartDate())
                .endDate(orderDTO.getEndDate())
                .parkingSpot(spot)
                .referenceNumber(orderDTO.getReferenceNumber())
                .days(orderDTO.getDays())
                .price(orderDTO.getPrice())
                .build();
    }

    @Transactional()
    public boolean manageOrder(OrderDTO orderDTO){
        if(validateStartEndDate(orderDTO)) {
            Optional<ParkingSpot> freeParkingSpot = parkingService.getFreeParkingSpot(orderDTO);
            if(freeParkingSpot.isPresent()){
                orderDTO.setReferenceNumber(generateReferenceNumber());
                orderDTO.setParkingSpot(freeParkingSpot.get());
                orderDTO.setPrice(calculatePrice(orderDTO));

                Order order = buildOrder(orderDTO, freeParkingSpot.get());

                orderRepository.save(order);

                freeParkingSpot.get().getOrderList().add(order);

                parkingSpotRepository.save(freeParkingSpot.get());

                mailSenderService.sendConfirmationMail(order);

                return true;
            }
        }
        else {
            return false;
        }
        return false;
    }


}
