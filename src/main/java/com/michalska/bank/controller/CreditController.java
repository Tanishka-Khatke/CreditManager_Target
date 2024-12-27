// package com.michalska.bank.controller;
 
// import com.michalska.bank.entities.Credit; 
// import com.michalska.bank.entities.Customer; 
// import com.michalska.bank.entities.Product; 
// import com.michalska.bank.model.dto.CreditDto; 
// import com.michalska.bank.model.dto.CustomerDto; 
// import com.michalska.bank.model.dto.ProductDto; 
// import com.michalska.bank.service.CreditService; 
// import com.michalska.bank.service.CustomerService; 
// import com.michalska.bank.service.ProductService; 
// import lombok.NonNull; 
// import org.springframework.beans.factory.annotation.Autowired; 
// import org.springframework.beans.factory.annotation.Value; 
// import org.springframework.http.HttpStatus; 
// import org.springframework.http.ResponseEntity; 
// import org.springframework.stereotype.Controller; 
// import org.springframework.ui.Model; 
// import org.springframework.web.bind.annotation.*; 
// import java.util.List; 
// import java.util.stream.Collectors; 
// @Controller 
// @RequestMapping 
// public class CreditController { 
//     private final CreditService creditService; 
//     private final CustomerService customerService; 
//     private final ProductService productService; 
//     @Value("${error.message}") 
//     private String errorMessage; 
//     @Autowired 
//     public CreditController(@NonNull CreditService creditService, CustomerService customerService, ProductService productService) { 
//         this.creditService = creditService; 
//         this.customerService = customerService; 
//         this.productService = productService; 
//     } 
//     @GetMapping(value = "/home") 
//     public String showHome(Model model) { 
//         return "home"; 
//     } 
//     @GetMapping(value = "/createCredit") 
//     public String showForm(Model model) { 
//         Credit credit = new Credit(); 
//         model.addAttribute("credit", credit); 
//         model.addAttribute("customer", new Customer()); 
//         model.addAttribute("product", new Product()); 
//         return "form"; 
//     } 
//     @PostMapping("/createCredit") 
//     public String creditSubmit(@ModelAttribute Credit credit, Model model) { 
//         try { 
//             if (this.creditService.validate(credit)) { 
//                 this.creditService.createCredit(credit); 
//                 model.addAttribute("credit", credit); 
//                 return "result"; 
//             } else { 
//                 model.addAttribute("errorMessage", errorMessage); 
//                 return "form"; 
//             } 
//         } catch (Exception e) { 
//             model.addAttribute("errorMessage", "An error occurred while processing the credit: " + e.getMessage()); 
//             e.printStackTrace(); 
//             return "form"; 
//         } 
//     } 
//     @GetMapping(value = {"/getList"}) 
//     public String creditList(Model model) { 
//         try { 
//             model.addAttribute("credit", creditService.getCredits()); 
//             return "creditList"; 
//         } catch (Exception e) { 
//             model.addAttribute("errorMessage", "An error occurred while retrieving the credit list: " + e.getMessage()); 
//             e.printStackTrace(); 
//             return "error"; 
//         } 
//     } 
//     @PostMapping("/create/credit") 
//     @ResponseBody 
//     public ResponseEntity<CreditDto> createCredit(@RequestBody final CreditDto creditDto) { 
//         try { 
//             Credit credit = creditService.createCredit(Credit.from(creditDto)); 
//             return new ResponseEntity<>(CreditDto.from(credit), HttpStatus.OK); 
//         } catch (Exception e) { 
//             System.err.println("Error creating credit: " + e.getMessage()); 
//             e.printStackTrace(); 
//             return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); 
//         } 
//     } 
//     @GetMapping("/get/credits") 
//     public ResponseEntity<List<CreditDto>> getCredits() { 
//         try { 
//             List<Credit> credits = creditService.getCredits(); 
//             List<CreditDto> creditsDto = credits.stream().map(CreditDto::from).collect(Collectors.toList()); 
//             return new ResponseEntity<>(creditsDto, HttpStatus.OK); 
//         } catch (Exception e) { 
//             System.err.println("Error retrieving credits: " + e.getMessage()); 
//             e.printStackTrace(); 
//             return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); 
//         } 
//     } 
//     @PostMapping("/create/customer") 
//     public ResponseEntity<CustomerDto> createCustomer(@RequestBody final CustomerDto customerDto) { 
//         try { 
//             Customer customer = customerService.createCustomer(Customer.from(customerDto)); 
//             return new ResponseEntity<>(CustomerDto.from(customer), HttpStatus.OK); 
//         } catch (Exception e) { 
//             System.err.println("Error creating customer: " + e.getMessage()); 
//             e.printStackTrace(); 
//             return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); 
//         } 
//     } 
//     @GetMapping("/get/customers") 
//     public ResponseEntity<List<CustomerDto>> getCustomers() { 
//         try { 
//             List<Customer> customers = customerService.getCustomers(); 
//             List<CustomerDto> customersDto = customers.stream().map(CustomerDto::from).collect(Collectors.toList()); 
//             return new ResponseEntity<>(customersDto, HttpStatus.OK); 
//         } catch (Exception e) { 
//             System.err.println("Error retrieving customers: " + e.getMessage()); 
//             e.printStackTrace(); 
//             return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); 
//         } 
//     } 
//     @PostMapping("/create/product") 
//     public ResponseEntity<ProductDto> createProduct(@RequestBody final ProductDto productDto) { 
//         try { 
//             Product product = productService.createProduct(Product.from(productDto)); 
//             return new ResponseEntity<>(ProductDto.from(product), HttpStatus.OK); 
//         } catch (Exception e) { 
//             System.err.println("Error creating product: " + e.getMessage()); 
//             e.printStackTrace(); 
//             return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); 
//         } 
//     } 
//     @GetMapping("/get/products") 
//     public ResponseEntity<List<ProductDto>> getProducts() { 
//         try { 
//             List<Product> products = productService.getProducts(); 
//             List<ProductDto> productDto = products.stream().map(ProductDto::from).collect(Collectors.toList()); 
//             return new ResponseEntity<>(productDto, HttpStatus.OK); 
//         } catch (Exception e) { 
//             System.err.println("Error retrieving products: " + e.getMessage()); 
//             e.printStackTrace(); 
//             return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); 
//         } 
//     } 
// }

package com.michalska.bank.controller;

import com.michalska.bank.entities.Credit;
import com.michalska.bank.entities.Customer;
import com.michalska.bank.entities.Product;
import com.michalska.bank.model.dto.CreditDto;
import com.michalska.bank.model.dto.CustomerDto;
import com.michalska.bank.model.dto.ProductDto;
import com.michalska.bank.service.CreditService;
import com.michalska.bank.service.CustomerService;
import com.michalska.bank.service.ProductService;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping
public class CreditController {

    private final CreditService creditService;
    private final CustomerService customerService;
    private final ProductService productService;
    @Value("${error.message}")
    private String errorMessage;

    @Autowired
    public CreditController(@NonNull @Lazy CreditService creditService, @Lazy CustomerService customerService, @Lazy ProductService productService) {
        this.creditService = creditService;
        this.customerService = customerService;
        this.productService = productService;
    }

    @GetMapping(value = "/home")
    public String showHome(Model model) {
        return "home";
    }

    @GetMapping(value = "/createCredit")
    public String showForm(Model model) {
        Credit credit = new Credit();
        model.addAttribute("credit", credit);
        model.addAttribute("customer", new Customer());
        model.addAttribute("product", new Product());
        return "form";
    }

    @PostMapping("/createCredit")
    public String creditSubmit(@ModelAttribute Credit credit, Model model) {

        if (this.creditService.validate(credit)) {
            this.creditService.createCredit(credit);
            model.addAttribute("credit", credit);
            return "result";
        } else {
            model.addAttribute("errorMessage", errorMessage);
            return "form";
        }
    }

    @GetMapping(value = {"/getList"})
    public String creditList(Model model) {
        model.addAttribute("credit", creditService.getCredits());
        return "creditList";
    }

    @PostMapping("/create/credit")
    @ResponseBody
    public ResponseEntity<CreditDto> createCredit(@RequestBody final CreditDto creditDto) {
        Credit credit = creditService.createCredit(Credit.from(creditDto));
        return new ResponseEntity<>(CreditDto.from(credit), HttpStatus.OK);
    }

    @GetMapping("/get/credits")
    public ResponseEntity<List<CreditDto>> getCredits() {
        List<Credit> credits = creditService.getCredits();
        List<CreditDto> creditsDto = credits.stream().map(CreditDto::from).collect(Collectors.toList());
        return new ResponseEntity<>(creditsDto, HttpStatus.OK);
    }

    @PostMapping("/create/customer")
    public ResponseEntity<CustomerDto> createCustomer(@RequestBody final CustomerDto customerDto) {
        Customer customer = customerService.createCustomer(Customer.from(customerDto));
        return new ResponseEntity<>(CustomerDto.from(customer), HttpStatus.OK);
    }

    @GetMapping("/get/customers")
    public ResponseEntity<List<CustomerDto>> getCustomers() {
        List<Customer> customers = customerService.getCustomers();
        List<CustomerDto> customersDto = customers.stream().map(CustomerDto::from).collect(Collectors.toList());
        return new ResponseEntity<>(customersDto, HttpStatus.OK);
    }

    @PostMapping("/create/product")
    public ResponseEntity<ProductDto> createProduct(@RequestBody final ProductDto productDto) {
        Product product = productService.createProduct(Product.from(productDto));
        return new ResponseEntity<>(ProductDto.from(product), HttpStatus.OK);
    }

    @GetMapping("/get/products")
    public ResponseEntity<List<ProductDto>> getProducts() {
        List<Product> products = productService.getProducts();
        List<ProductDto> productDto = products.stream().map(ProductDto::from).collect(Collectors.toList());
        return new ResponseEntity<>(productDto, HttpStatus.OK);
    }
}
