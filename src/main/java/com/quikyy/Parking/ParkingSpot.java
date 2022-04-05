package com.quikyy.Parking;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.quikyy.Order.Order;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
public class ParkingSpot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @JsonManagedReference
    @OneToMany(fetch = FetchType.EAGER)
    private List<Order> orderList = new ArrayList<>();



}
