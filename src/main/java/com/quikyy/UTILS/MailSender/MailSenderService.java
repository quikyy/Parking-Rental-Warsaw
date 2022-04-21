package com.quikyy.UTILS.MailSender;
import com.quikyy.Order.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class MailSenderService {
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private JavaMailSender javaMailSender;
    ExecutorService singleThreadExecutor = Executors.newFixedThreadPool(10);

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

            createThreadAndSendMessage(message);
    }

    public void createThreadAndSendMessage(SimpleMailMessage message){
        singleThreadExecutor.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    //todo vertex mail
                    javaMailSender.send(message);
                } catch (MailException e) {
                    e.printStackTrace();
                }
            }
        });
    }

}

