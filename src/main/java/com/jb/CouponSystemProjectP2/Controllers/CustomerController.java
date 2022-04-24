package com.jb.CouponSystemProjectP2.Controllers;

import com.jb.CouponSystemProjectP2.Beans.Customer;
import com.jb.CouponSystemProjectP2.Services.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customer")
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService;

    @GetMapping("/get")
    public ResponseEntity<?> getAllCustomers() {
        return new ResponseEntity<>(customerService.readAllCustomers(), HttpStatus.OK);
    }

    @GetMapping("/customers/{id}")
    public ResponseEntity<?> getCustomerById(@PathVariable int id) {
        return new ResponseEntity<>(customerService.readCustomerById(id), HttpStatus.ACCEPTED);
    }

    @PostMapping("/add")
    public ResponseEntity<?> createNewCustomer(@RequestBody Customer customer) {
        customerService.createCustomer(customer);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/update")
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    public void updateCustomer(@RequestBody Customer customer) {
        try {
            customerService.updateCustomer(customer);
        } catch (Exception e) {
            System.out.println("Failed to update customer"); // todo: customize exception.
        }
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    public void deleteCustomer(@PathVariable int id) throws Exception {
        try {
            customerService.deleteCustomer(id);
        } catch (Exception e) {
            System.out.println("Failed to delete customer"); //todo:customize exception
        }
    }
}

