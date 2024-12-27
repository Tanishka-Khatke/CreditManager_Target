package com.michalska.bank.repository; 
import com.michalska.bank.entities.Product; 
import org.springframework.data.repository.CrudRepository; 
import org.springframework.stereotype.Repository; 
/** 
 * Repository interface for managing Product entities. 
 * Extends Spring's CrudRepository to provide basic CRUD operations. 
 */ 
@Repository 
public interface ProductRepository extends CrudRepository<Product, Long> { 
}