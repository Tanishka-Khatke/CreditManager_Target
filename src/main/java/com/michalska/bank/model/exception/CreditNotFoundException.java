package com.michalska.bank.model.exception; 
import java.text.MessageFormat; 
public final class CreditNotFoundException extends RuntimeException { 
    public CreditNotFoundException(Long id) { 
        super(MessageFormat.format("Could not find credit with id: {0}", id)); 
    } 
}