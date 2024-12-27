package com.michalska.bank.entities;

import com.michalska.bank.model.dto.CustomerDto;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "customer")
public class Customer implements Serializable {
    @Id
    @Column(name = "credit_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String firstName;
    @Column
    private String lastName;
    @Column
    private Long peselNumber;
    @OneToOne
    @PrimaryKeyJoinColumn(name = "credit_id")
    private Credit credit;

    public Customer(int id, String firstName, String lastName, Long peselNumber) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.peselNumber = peselNumber;
    }

    public Customer() {
    }

    public static Customer from(CustomerDto customerDto) {
        Customer customer = new Customer();
        customer.setId(customerDto.getId());
        customer.setFirstName(customerDto.getFirstName());
        customer.setLastName(customerDto.getLastName());
        customer.setPeselNumber(customerDto.getPeselNumber());
        return customer;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Long getPeselNumber() {
        return peselNumber;
    }

    public void setPeselNumber(Long peselNumber) {
        this.peselNumber = peselNumber;
    }

    public Credit getCredit() {
        return credit;
    }

    public void setCredit(Credit credit) {
        this.credit = credit;
    }
}

// package com.michalska.bank.entities;

// import javax.persistence.*;
// import java.io.Serializable;

// @Entity
// @Table(name = "customer")
// public class Customer implements Serializable {
//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     private int id;

//     @Column
//     private String firstName;

//     @Column
//     private String lastName;

//     @Column
//     private Long peselNumber;

//     @OneToOne
//     @PrimaryKeyJoinColumn(name = "credit_id")
//     private Credit credit;

//     public Customer(int id, String firstName, String lastName, Long peselNumber) {
//         this.id = id;
//         this.firstName = firstName;
//         this.lastName = lastName;
//         this.peselNumber = peselNumber;
//     }

//     public Customer() {
//     }

//     public int getId() {
//         return id;
//     }

//     public void setId(int id) {
//         this.id = id;
//     }

//     public String getFirstName() {
//         return firstName;
//     }

//     public void setFirstName(String firstName) {
//         this.firstName = firstName;
//     }

//     public String getLastName() {
//         return lastName;
//     }

//     public void setLastName(String lastName) {
//         this.lastName = lastName;
//     }

//     public Long getPeselNumber() {
//         return peselNumber;
//     }

//     public void setPeselNumber(Long peselNumber) {
//         this.peselNumber = peselNumber;
//     }

//     public Credit getCredit() {
//         return credit;
//     }

//     public void setCredit(Credit credit) {
//         this.credit = credit;
//     }
// }