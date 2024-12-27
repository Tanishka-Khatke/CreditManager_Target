// package com.michalska.bank.entities;

// import com.michalska.bank.model.dto.ProductDto;

// import jakarta.persistence.*;
// import java.io.Serializable;

// @Entity
// @Table(name = "product")
// public class Product implements Serializable {
//     @Id
//     @Column(name = "credit_id")
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     private int id;
//     @Column
//     private String productName;
//     @Column
//     private Long productValue;
//     @OneToOne
//     @PrimaryKeyJoinColumn(name = "credit_id")
//     private Credit credit;

//     public Product(int id, String productName, Long productValue) {
//         this.id = id;
//         this.productName = productName;
//         this.productValue = productValue;
//     }

//     public Product() {
//     }

//     public static Product from(ProductDto productDto) {
//         var product = new Product();
//         product.setId(productDto.getId());
//         product.setProductName(productDto.getProductName());
//         product.setProductValue(productDto.getProductValue());
//         return product;
//     }

//     // Getters and Setters
//     public int getId() {
//         return id;
//     }

//     public void setId(int id) {
//         this.id = id;
//     }

//     public String getProductName() {
//         return productName;
//     }

//     public void setProductName(String productName) {
//         this.productName = productName;
//     }

//     public Long getProductValue() {
//         return productValue;
//     }

//     public void setProductValue(Long productValue) {
//         this.productValue = productValue;
//     }

//     public Credit getCredit() {
//         return credit;
//     }

//     public void setCredit(Credit credit) {
//         this.credit = credit;
//     }
// }

package com.michalska.bank.entities;

import com.michalska.bank.model.dto.ProductDto;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "product")
public class Product implements Serializable {
    @Id
    @Column(name = "credit_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String productName;

    @Column
    private Long productValue;
    
    @OneToOne
    @PrimaryKeyJoinColumn(name = "credit_id")
     private Credit credit;
    public Product(int id, String productName, Long productValue) {
        this.id = id;
        this.productName = productName;
        this.productValue = productValue;
    }

    public Product() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Long getProductValue() {
        return productValue;
    }

    public void setProductValue(Long productValue) {
        this.productValue = productValue;
    }

    public static Product from(ProductDto productDto) {
        Product product = new Product();
        product.setId(productDto.getId());
        product.setProductName(productDto.getProductName());
        product.setProductValue(productDto.getProductValue());
        return product;
    }
}