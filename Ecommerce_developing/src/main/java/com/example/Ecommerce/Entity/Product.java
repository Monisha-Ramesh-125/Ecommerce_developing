package com.example.Ecommerce.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "products",

      uniqueConstraints = { @UniqueConstraint(
                name = "sku_unique",
                columnNames = "stock keeping unit"
        )}
)
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="stock keeping unit",nullable = false)
    private String sku;
  //  @NotNull// is for Bean Validation to ensure that a field value is not null.
    @NotBlank//is for Bean Validation to ensure that a string value is not null and not just whitespace.
    private String name;
    @NotBlank
    @Size(min = 6, message = "Product description must contain atleast 6 characters")
    private String description;
//    @NotBlank
    private double price;
    private boolean active = true;
//   @NotBlank
    private String imageUrl;

    private int discount=0;
    private double specialPrice;
    @CreationTimestamp
    private LocalDateTime dateCreated;
    @UpdateTimestamp
    private LocalDateTime lastUpdated;

    @Enumerated(EnumType.STRING)
    private ProductStatus status;

    @Enumerated(EnumType.STRING)
    private CategoryEnum category;

    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JsonIgnore
    private Seller seller;


}
