// package com.michalska.bank.entities;

// import com.michalska.bank.model.dto.CreditDto;

// import jakarta.persistence.*;
// import java.io.Serializable;

// @Entity
// @Table(name = "credit")
// public class Credit implements Serializable {
//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     @Column(name = "id")    
//     private int id;
//     @Column(name = "credit_name")
//     private String creditName;

//     // @OneToOne(cascade = CascadeType.ALL, mappedBy = "credit")
//     // private Customer customer;

//     // @OneToOne(cascade = CascadeType.ALL, mappedBy = "credit")
//     // private Product product;

//     @ManyToOne
//     @JoinColumn(name = "customer_id")
//     private Customer customer;

//     @ManyToOne
//     @JoinColumn(name = "product_id")
//     private Product product;

//     public Credit(int id, String creditName, Customer customer, Product product) {
//         this.id = id;
//         this.creditName = creditName;
//         this.customer = customer;
//         this.product = product;
//     }

//     public Credit() {
//     }

//     public static Credit from(CreditDto creditDto) {
//         Credit credit = new Credit();
//         credit.setCreditName(creditDto.getCreditName());
//         return credit;
//     }

//     public Customer getCustomer() {
//         return customer;
//     }

//     public void setCustomer(Customer customer) {
//         this.customer = customer;
//     }

//     public Product getProduct() {
//         return product;
//     }

//     public void setProduct(Product product) {
//         this.product = product;
//     }

//     public int getId() {
//         return id;
//     }

//     public void setId(int id) {
//         this.id = id;
//     }

//     public String getCreditName() {
//         return creditName;
//     }

//     public void setCreditName(String creditName) {
//         this.creditName = creditName;
//     }
// }

package com.michalska.bank.entities;

import com.michalska.bank.model.dto.CreditDto;

import jakarta.persistence.*;
import java.io.Serializable;


@Entity
@Table(name = "credit")
public class Credit implements Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private String creditName;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "credit")
    private Customer customer;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "credit")
    private Product product;

    public Credit(int id, String creditName, Customer customer, Product product) {
        this.id = id;
        this.creditName = creditName;
        this.customer = customer;
        this.product = product;
    }

    public Credit() {

    }

    public static Credit from(CreditDto creditDto) {
        Credit credit = new Credit();
        credit.setCreditName(creditDto.getCreditName());
        return credit;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCreditName() {
        return creditName;
    }

    public void setCreditName(String creditName) {
        this.creditName = creditName;
    }

}
