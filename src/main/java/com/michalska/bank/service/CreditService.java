// package com.michalska.bank.service; 
// import com.michalska.bank.entities.Credit; 
// import com.michalska.bank.entities.Customer; 
// import com.michalska.bank.entities.Product; 
// import com.michalska.bank.model.exception.CreditNotFoundException; 
// import com.michalska.bank.model.exception.CustomerNotFoundException; 
// import com.michalska.bank.model.exception.ProductNotFoundException; 
// import com.michalska.bank.repository.CreditRepository; 
// import com.michalska.bank.repository.CustomerRepository; 
// import com.michalska.bank.repository.ProductRepository; 
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
// public class CreditService { 
//     private final CreditRepository creditRepository; 
//     private final CustomerRepository customerRepository; 
//     private final ProductRepository productRepository; 
//     private final ExecutorService executorService = Executors.newFixedThreadPool(4); 
//     @Autowired 
//     public CreditService(@NonNull CreditRepository creditRepository, CustomerRepository customerRepository, ProductRepository productRepository) { 
//         this.creditRepository = creditRepository; 
//         this.customerRepository = customerRepository; 
//         this.productRepository = productRepository; 
//     } 
//     public List<Credit> findAllCredits() { 
//         return (List<Credit>) this.creditRepository.findAll(); 
//     } 
//     public Credit createCredit(Credit credit) { 
//         try { 
//             Credit savedCredit = creditRepository.save(credit); 
//             System.out.println("Credit created successfully: " + savedCredit); 
//             return savedCredit; 
//         } catch (Exception e) { 
//             System.err.println("Error creating credit: " + e.getMessage()); 
//             e.printStackTrace(); 
//             throw e; 
//         } 
//     } 
//     public boolean validate(Credit credit) { 
//         return credit.getCreditName() != null && credit.getCreditName().length() > 0 
//                 && credit.getProduct().getProductName() != null && credit.getProduct().getProductName().length() > 0 
//                 && credit.getProduct().getProductValue() != null && credit.getProduct().getProductValue().toString().length() > 0 
//                 && credit.getCustomer().getFirstName() != null && credit.getCustomer().getFirstName().length() > 0 
//                 && credit.getCustomer().getLastName() != null && credit.getCustomer().getLastName().length() > 0 
//                 && credit.getCustomer().getPeselNumber() != null && credit.getCustomer().getPeselNumber().toString().length() == 11; 
//     } 
//     public Credit getCreditById(Long id) { 
//         try { 
//             Credit credit = creditRepository.findById(id).orElseThrow(() -> new CreditNotFoundException(id)); 
//             System.out.println("Retrieved credit by ID: " + credit); 
//             return credit; 
//         } catch (Exception e) { 
//             System.err.println("Error retrieving credit by ID: " + e.getMessage()); 
//             e.printStackTrace(); 
//             throw e; 
//         } 
//     } 
//     public List<Credit> getCredits() { 
//         try { 
//             Future<List<Credit>> futureCredits = executorService.submit(() -> 
//                 StreamSupport.stream(creditRepository.findAll().spliterator(), false).collect(Collectors.toList()) 
//             ); 
//             List<Credit> credits = futureCredits.get(); 
//             System.out.println("Retrieved credits: " + credits); 
//             return credits; 
//         } catch (InterruptedException e) { 
//             Thread.currentThread().interrupt(); 
//             System.err.println("Thread was interrupted while retrieving credits: " + e.getMessage()); 
//             e.printStackTrace(); 
//             throw new RuntimeException("Thread was interrupted", e); 
//         } catch (ExecutionException e) { 
//             System.err.println("Execution exception occurred while retrieving credits: " + e.getMessage()); 
//             e.printStackTrace(); 
//             throw new RuntimeException("Failed to retrieve credits", e); 
//         } catch (Exception e) { 
//             System.err.println("Error retrieving credits: " + e.getMessage()); 
//             e.printStackTrace(); 
//             throw new RuntimeException("Failed to retrieve credits", e); 
//         } 
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
//             List<Customer> customers = StreamSupport.stream(customerRepository.findAll().spliterator(), false).collect(Collectors.toList()); 
//             System.out.println("Retrieved customers: " + customers); 
//             return customers; 
//         } catch (Exception e) { 
//             System.err.println("Error retrieving customers: " + e.getMessage()); 
//             e.printStackTrace(); 
//             throw e; 
//         } 
//     } 
//     public Customer getCustomerById(Long id) { 
//         try { 
//             Customer customer = customerRepository.findById(id).orElseThrow(() -> new CustomerNotFoundException(id)); 
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
//     public Product createProduct(Product product) { 
//         try { 
//             Product savedProduct = productRepository.save(product); 
//             System.out.println("Product created successfully: " + savedProduct); 
//             return savedProduct; 
//         } catch (Exception e) { 
//             System.err.println("Error creating product: " + e.getMessage()); 
//             e.printStackTrace(); 
//             throw e; 
//         } 
//     } 
//     public List<Product> getProducts() { 
//         try { 
//             List<Product> products = StreamSupport.stream(productRepository.findAll().spliterator(), false).collect(Collectors.toList()); 
//             System.out.println("Retrieved products: " + products); 
//             return products; 
//         } catch (Exception e) { 
//             System.err.println("Error retrieving products: " + e.getMessage()); 
//             e.printStackTrace(); 
//             throw e; 
//         } 
//     } 
//     public Product getProductById(Long id) { 
//         try { 
//             Product product = productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException(id)); 
//             System.out.println("Retrieved product by ID: " + product); 
//             return product; 
//         } catch (Exception e) { 
//             System.err.println("Error retrieving product by ID: " + e.getMessage()); 
//             e.printStackTrace(); 
//             throw e; 
//         } 
//     } 
//     public List<Product> findAllProducts() { 
//         try { 
//             List<Product> products = (List<Product>) this.productRepository.findAll(); 
//             System.out.println("Found all products: " + products); 
//             return products; 
//         } catch (Exception e) { 
//             System.err.println("Error finding all products: " + e.getMessage()); 
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

import com.michalska.bank.entities.Credit;
import com.michalska.bank.entities.Customer;
import com.michalska.bank.entities.Product;
import com.michalska.bank.model.exception.CreditNotFoundException;
import com.michalska.bank.model.exception.CustomerNotFoundException;
import com.michalska.bank.model.exception.ProductNotFoundException;
import com.michalska.bank.repository.CreditRepository;
import com.michalska.bank.repository.CustomerRepository;
import com.michalska.bank.repository.ProductRepository;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class CreditService {

    private final CreditRepository creditRepository;
    private final CustomerRepository customerRepository;
    private final ProductRepository productRepository;

    @Autowired
    public CreditService(@NonNull @Lazy CreditRepository creditRepository, CustomerRepository customerRepository, ProductRepository productRepository) {
        this.creditRepository = creditRepository;
        this.customerRepository = customerRepository;
        this.productRepository = productRepository;
    }

    public List<Credit> findAllCredits() {
        return (List<Credit>) this.creditRepository.findAll();
    }

    public Credit createCredit(Credit credit) {
        return creditRepository.save(credit);
    }

    public boolean validate(Credit credit) {
        return credit.getCreditName() != null && credit.getCreditName().length() > 0
                && credit.getProduct().getProductName() != null && credit.getProduct().getProductName().length() > 0
                && credit.getProduct().getProductValue() != null && credit.getProduct().getProductValue().toString().length() > 0
                && credit.getCustomer().getFirstName() != null && credit.getCustomer().getFirstName().length() > 0
                && credit.getCustomer().getLastName() != null && credit.getCustomer().getLastName().length() > 0
                && credit.getCustomer().getPeselNumber() != null && credit.getCustomer().getPeselNumber().toString().length() == 11;
    }

    public Credit getCreditById(Long id) {
        return creditRepository.findById(id).orElseThrow(() -> new CreditNotFoundException(id));
    }

    public List<Credit> getCredits() {
        return StreamSupport.stream(creditRepository.findAll().spliterator(), false).collect(Collectors.toList());
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


    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    public List<Product> getProducts() {
        return StreamSupport.stream(productRepository.findAll().spliterator(), false).collect(Collectors.toList());
    }

    public Product getProductById(Long id) {
        return productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException(id));
    }

    public List<Product> findAllProducts() {
        return (List<Product>) this.productRepository.findAll();
    }

}
