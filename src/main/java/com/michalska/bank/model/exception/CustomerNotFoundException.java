package com.michalska.bank.model.exception; 
import java.text.MessageFormat; 
public final class CustomerNotFoundException extends RuntimeException { 
    public CustomerNotFoundException(Long id) { 
        super(MessageFormat.format("Could not find customer with id: {0}", id)); 
    } 
}