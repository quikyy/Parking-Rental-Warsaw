package com.quikyy;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
public class ParkingApplication {

    public static void main(String[] args){
        SpringApplication.run(ParkingApplication.class, args);
    }
   }
