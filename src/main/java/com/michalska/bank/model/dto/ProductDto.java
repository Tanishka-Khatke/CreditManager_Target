package com.michalska.bank.model.dto;

import com.michalska.bank.entities.Product;
import lombok.Data;

@Data
public class ProductDto {
    private int id;
    private String productName;
    private Long productValue;

    public static ProductDto from(Product product) {
        ProductDto productDto = new ProductDto();
        productDto.setId(product.getId());
        productDto.setProductName(product.getProductName());
        productDto.setProductValue(product.getProductValue());
        return productDto;

    }
}
