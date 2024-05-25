//package com.example.Ecommerce.Entity;
//
//import jakarta.validation.constraints.NotBlank;
//import jakarta.validation.constraints.Size;
//import lombok.*;
//import jakarta.persistence.*;
//
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Getter
//@Setter
//@Entity
//@Builder
//@NoArgsConstructor
//@AllArgsConstructor
//@Table(name = "product_categories")
//public class Category {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private long id;
//    @Size(min = 3, message = "category description must contain atleast 6 characters")
//    @NotBlank
//    private String categoryName;
//    @Size(min = 6, message = "category description must contain atleast 6 characters")
//    private String categoryDescription;
//
//    @OneToMany(cascade = {
//            CascadeType.PERSIST,
//            CascadeType.MERGE,
//    }, fetch = FetchType.LAZY,
//            mappedBy = "category")
//    private List<Product> products = new ArrayList<>();
//}
