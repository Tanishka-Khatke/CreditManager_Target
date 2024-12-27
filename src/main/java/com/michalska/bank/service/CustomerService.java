// package com.michalska.bank.service; 
// import com.michalska.bank.entities.Customer; 
// import com.michalska.bank.model.exception.BankException; 
// import com.michalska.bank.model.exception.CustomerNotFoundException; 
// import com.michalska.bank.repository.CustomerRepository; 
// import lombok.NonNull; 
// import org.springframework.beans.factory.annotation.Autowired; 
// import org.springframework.stereotype.Service; 
// import java.util.List; 
// import java.util.concurrent.ExecutorService; 
// import java.util.concurrent.Executors; 
// import java.util.concurrent.Future; 
// import java.util.concurrent.ExecutionException; 
// import java.util.stream.Collectors; 
// import java.util.stream.StreamSupport; 
// @Service 
// public class CustomerService { 
//     private final CustomerRepository customerRepository; 
//     private final ExecutorService executorService = Executors.newFixedThreadPool(4); 
//     @Autowired 
//     public CustomerService(@NonNull CustomerRepository customerRepository) { 
//         this.customerRepository = customerRepository; 
//     } 
//     public Customer createCustomer(Customer customer) { 
//         try { 
//             Customer savedCustomer = customerRepository.save(customer); 
//             System.out.println("Customer created successfully: " + savedCustomer); 
//             return savedCustomer; 
//         } catch (Exception e) { 
//             System.err.println("Error creating customer: " + e.getMessage()); 
//             e.printStackTrace(); 
//             throw e; 
//         } 
//     } 
//     public List<Customer> getCustomers() { 
//         try { 
//             Future<List<Customer>> futureCustomers = executorService.submit(() -> 
//                 StreamSupport.stream(customerRepository.findAll().spliterator(), false).collect(Collectors.toList()) 
//             ); 
//             List<Customer> customers = futureCustomers.get(); 
//             System.out.println("Retrieved customers: " + customers); 
//             return customers; 
//         } catch (InterruptedException e) { 
//             Thread.currentThread().interrupt(); 
//             System.err.println("Thread was interrupted while retrieving customers: " + e.getMessage()); 
//             e.printStackTrace(); 
//             throw new RuntimeException("Thread was interrupted", e); 
//         } catch (ExecutionException e) { 
//             System.err.println("Execution exception occurred while retrieving customers: " + e.getMessage()); 
//             e.printStackTrace(); 
//             throw new RuntimeException("Failed to retrieve customers", e); 
//         } catch (Exception e) { 
//             System.err.println("Error retrieving customers: " + e.getMessage()); 
//             e.printStackTrace(); 
//             throw new RuntimeException("Failed to retrieve customers", e); 
//         } 
//     } 
//     public Customer getCustomerById(Long id) { 
//         try { 
//             Customer customer = customerRepository.findById(id).orElseThrow(() -> { 
//                 BankException exception = new CustomerNotFoundException(id); 
//                 if (exception instanceof CustomerNotFoundException e) { 
//                     System.err.println("Customer not found: " + e.getMessage()); 
//                     throw e; 
//                 } 
//                 throw exception; // Fallback 
//             }); 
//             System.out.println("Retrieved customer by ID: " + customer); 
//             return customer; 
//         } catch (Exception e) { 
//             System.err.println("Error retrieving customer by ID: " + e.getMessage()); 
//             e.printStackTrace(); 
//             throw e; 
//         } 
//     } 
//     public List<Customer> findAllCustomers() { 
//         try { 
//             List<Customer> customers = (List<Customer>) this.customerRepository.findAll(); 
//             System.out.println("Found all customers: " + customers); 
//             return customers; 
//         } catch (Exception e) { 
//             System.err.println("Error finding all customers: " + e.getMessage()); 
//             e.printStackTrace(); 
//             throw e; 
//         } 
//     } 
//     // Ensure to shut down the executor service when it's no longer needed 
//     public void shutdown() { 
//         executorService.shutdown(); 
//     } 
// }

package com.michalska.bank.service;

import com.michalska.bank.entities.Customer;
import com.michalska.bank.model.exception.CustomerNotFoundException;
import com.michalska.bank.repository.CustomerRepository;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    @Autowired
    @Lazy
    public CustomerService(@NonNull @Lazy CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer createCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    public List<Customer> getCustomers() {
        return StreamSupport.stream(customerRepository.findAll().spliterator(), false).collect(Collectors.toList());
    }

    public Customer getCustomerById(Long id) {
        return customerRepository.findById(id).orElseThrow(() -> new CustomerNotFoundException(id));
    }

    public List<Customer> findAllCustomers() {
        return (List<Customer>) this.customerRepository.findAll();
    }
}