package com.smartcafe.smartcafe.model;
import jakarta.persistence.*;
import lombok.*;
import java.time.*;
@Entity 
@Setter 
@Getter 
@AllArgsConstructor 
@NoArgsConstructor 
public class Booking {
    @Id 
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer id;
    private String customerName ;
    private String phone;
    private LocalDate bookingDate  ;
    private LocalTime startTime;
    private LocalTime endTime;
    private  int partySize;
    private String status;
    @ManyToOne
    @JoinColumn (name="table_id")
    private CafeTable cafeTable;
}
