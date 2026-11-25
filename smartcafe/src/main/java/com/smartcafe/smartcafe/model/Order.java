package com.smartcafe.smartcafe.model;
import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Entity 
@Setter 
@Getter 
@NoArgsConstructor 
@AllArgsConstructor 
@Table (name="Orders")
public class Order {
    @Id 
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer id ;
    private double total;
    private String status;
    private LocalDateTime createdAt;
    @ManyToOne 
    @JoinColumn (name="session_id")
    private TableSession tableSession;

    
}
