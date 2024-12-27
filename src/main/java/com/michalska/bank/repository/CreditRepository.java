package com.michalska.bank.repository; 
import com.michalska.bank.entities.Credit; 
import org.springframework.data.repository.CrudRepository; 
import org.springframework.stereotype.Repository; 
@Repository 
public interface CreditRepository extends CrudRepository<Credit, Long> { 
}