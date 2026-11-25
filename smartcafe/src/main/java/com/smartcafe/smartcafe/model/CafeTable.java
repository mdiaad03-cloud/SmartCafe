package com.smartcafe.smartcafe.model;
import java.util.UUID;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import lombok.*;
@Entity 
@Setter 
@Getter 
@AllArgsConstructor 
@NoArgsConstructor 
public class CafeTable {
    @Id 
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer id;
    private int number;
    private int seats;
    @Column(unique = true)
    private String qrToken;
    private String status;
    @PrePersist 
    public void generatedToken(){
        qrToken=UUID.randomUUID().toString();
    }
}
