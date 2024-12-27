// package com.michalska.bank.service; 
// import com.michalska.bank.entities.Product; 
// import com.michalska.bank.model.exception.BankException; 
// import com.michalska.bank.model.exception.ProductNotFoundException; 
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
// public class ProductService { 
//     private final ProductRepository productRepository; 
//     private final ExecutorService executorService = Executors.newFixedThreadPool(4); 
//     @Autowired 
//     public ProductService(@NonNull ProductRepository productRepository) { 
//         this.productRepository = productRepository; 
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
//             Future<List<Product>> futureProducts = executorService.submit(() -> 
//                 StreamSupport.stream(productRepository.findAll().spliterator(), false).collect(Collectors.toList()) 
//             ); 
//             List<Product> products = futureProducts.get(); 
//             System.out.println("Retrieved products: " + products); 
//             return products; 
//         } catch (InterruptedException e) { 
//             Thread.currentThread().interrupt(); 
//             System.err.println("Thread was interrupted while retrieving products: " + e.getMessage()); 
//             e.printStackTrace(); 
//             throw new RuntimeException("Thread was interrupted", e); 
//         } catch (ExecutionException e) { 
//             System.err.println("Execution exception occurred while retrieving products: " + e.getMessage()); 
//             e.printStackTrace(); 
//             throw new RuntimeException("Failed to retrieve products", e); 
//         } catch (Exception e) { 
//             System.err.println("Error retrieving products: " + e.getMessage()); 
//             e.printStackTrace(); 
//             throw new RuntimeException("Failed to retrieve products", e); 
//         } 
//     } 
//     public Product getProductById(Long id) { 
//         try { 
//             Product product = productRepository.findById(id).orElseThrow(() -> { 
//                 BankException exception = new ProductNotFoundException(id); 
//                 if (exception instanceof ProductNotFoundException e) { 
//                     System.err.println("Product not found: " + e.getMessage()); 
//                     throw e; 
//                 } 
//                 throw exception; // Fallback 
//             }); 
//             System.out.println("Retrieved product by ID: " + product); 
//             return product; 
//         } catch (Exception e) { 
//             System.err.println("Error retrieving product by ID: " + e.getMessage()); 
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

import com.michalska.bank.entities.Product;
import com.michalska.bank.repository.ProductRepository;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(@NonNull @Lazy ProductRepository productRepository, @Lazy ProductService productService) {
        this.productRepository = productRepository;
    }

    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    public List<Product> getProducts() {
        return StreamSupport.stream(productRepository.findAll().spliterator(), false).collect(Collectors.toList());
    }
}