package com.jb.CouponSystemProjectP2.Services;

import com.jb.CouponSystemProjectP2.Beans.Customer;
import com.jb.CouponSystemProjectP2.Repositories.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;


    public void createCustomer(Customer customer) {
        customerRepository.save(customer);
    }

    public Customer readCustomerById(int id) {
        Optional<Customer> customer = customerRepository.findById(id);
        return customer.orElse(null);
    }

    public List<Customer> readAllCustomers() {
        return customerRepository.findAll();
    }

    public void updateCustomer(Customer customer) {
        if (customerRepository.existsById(customer.getId())) {
            customerRepository.save(customer);
        } else {
            System.out.println("Customer not found");  // todo: customize exception.
        }
    }

    public void deleteCustomer(int id) {
        customerRepository.deleteById(id);
    }

    public boolean isCustomerExists(int id) {
        return customerRepository.existsById(id);
    }
}
