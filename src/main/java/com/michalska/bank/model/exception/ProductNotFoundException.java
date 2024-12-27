package com.michalska.bank.model.exception; 
import java.text.MessageFormat; 
public final class ProductNotFoundException extends RuntimeException { 
    public ProductNotFoundException(Long id) { 
        super(MessageFormat.format("Could not find product with id: {0}", id)); 
    } 
}