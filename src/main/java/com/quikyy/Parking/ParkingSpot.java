package com.quikyy.Parking;


import com.quikyy.Order.Order;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
public class ParkingSpot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String isTaken = "NO";

    @OneToMany(fetch = FetchType.EAGER)
    private List<Order> orderList = new ArrayList<>();

    public ParkingSpot() {
    }

}
