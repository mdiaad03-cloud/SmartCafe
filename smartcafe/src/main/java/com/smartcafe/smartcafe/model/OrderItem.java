package com.smartcafe.smartcafe.model;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Entity 
@Setter 
@Getter 
@NoArgsConstructor 
@AllArgsConstructor 
public class OrderItem {
    @Id 
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer id;
    private int quantity ;
    private double unitPrice ;
    @ManyToOne 
    @JoinColumn (name="order_id")
    private Order order;
    @ManyToOne 
    @JoinColumn (name="menu_item_id")
    private MenuItem menuItem;

    
}
