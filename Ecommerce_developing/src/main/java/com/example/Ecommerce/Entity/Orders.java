package com.example.Ecommerce.Entity;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.util.*;

@Entity
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;
    @Column(name="oder_tracking_number")
    private int orderTrackingNumber;

    @Column(name="total_quantity")
    private int totalQuantity;

    @Column(name="total_price")
    private BigDecimal totalPrice;

    @Column(name="status")
    private String status;

    @Column(name="date_created")
    @CreationTimestamp
    private Date dateCreated;

    @Column(name="last_updated")
    @CreationTimestamp
    private Date lateUpdated;


    @OneToOne(cascade = CascadeType.ALL,mappedBy = "order")
    private Address billingaddress;
    @OneToMany(cascade = CascadeType.ALL,fetch=FetchType.EAGER)
   @JoinColumn(name="order_id",referencedColumnName = "id")
    private List<OrderItem> orderItems = new ArrayList<>();
    public BigDecimal getTotalAmount(){
        BigDecimal amount = new BigDecimal(0.0);
        for(OrderItem item: this.orderItems){
            amount = amount.add(item.getPrice());
        }
        return amount;
    }
}