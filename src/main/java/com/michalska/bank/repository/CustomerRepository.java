package com.michalska.bank.repository; 
import com.michalska.bank.entities.Customer; 
import org.springframework.data.repository.CrudRepository; 
import org.springframework.stereotype.Repository; 
@Repository 
public interface CustomerRepository extends CrudRepository<Customer, Long> { 
}