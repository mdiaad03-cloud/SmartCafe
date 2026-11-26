package com.smartcafe.smartcafe.model;
import java.time.LocalDateTime;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Entity 
@Setter 
@Getter 
@NoArgsConstructor 
@AllArgsConstructor 
public class TableSession {
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String status ;
    private LocalDateTime createdAt;
    @PrePersist
    public void setcreatedAt(){
        this.createdAt=LocalDateTime.now();
    }
    @ManyToOne 
    @JoinColumn (name="table_id")
    private CafeTable cafeTable;
}
