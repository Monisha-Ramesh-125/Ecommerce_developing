package com.example.Ecommerce.Mapper;

import com.example.Ecommerce.DTO.ProductDTO;
import com.example.Ecommerce.Entity.Product;

public class ProductMapper {
    public static ProductDTO MaptoProductDto(Product product) {
        return ProductDTO.builder()
                .sku(product.getSku())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .active(product.isActive())
                .imageUrl(product.getImageUrl())
                .discount(product.getDiscount())
                .specialPrice(product.getSpecialPrice())
                .dateCreated(product.getDateCreated())
                .lastUpdated(product.getLastUpdated())
                .build();
    }
}
