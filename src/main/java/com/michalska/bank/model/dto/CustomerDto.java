package com.michalska.bank.model.dto;

import com.michalska.bank.entities.Customer;
import lombok.Data;

@Data
public class CustomerDto {
    private int id;
    private String firstName;
    private String lastName;
    private Long peselNumber;

    public static CustomerDto from(Customer customer) {
        CustomerDto customerDto = new CustomerDto();
        customerDto.setId(customer.getId());
        customerDto.setFirstName(customer.getFirstName());
        customerDto.setLastName(customer.getLastName());
        customerDto.setPeselNumber(customer.getPeselNumber());
        return customerDto;
    }
}
