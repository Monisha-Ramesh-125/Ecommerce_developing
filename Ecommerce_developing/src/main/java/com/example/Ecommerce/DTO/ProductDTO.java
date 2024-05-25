package com.example.Ecommerce.DTO;



import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductDTO {
    private String sku;
    private String name;
    private String description;
    private double price;
    private boolean active;
    private String imageUrl;
    private double discount;
    private double specialPrice;
    private LocalDateTime dateCreated;
    private LocalDateTime lastUpdated;

}
