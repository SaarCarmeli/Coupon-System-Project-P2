package com.jb.CouponSystemProjectP2.Controllers;

import com.jb.CouponSystemProjectP2.Beans.Company;
import com.jb.CouponSystemProjectP2.Beans.Customer;
import com.jb.CouponSystemProjectP2.Exceptions.CompanyException;
import com.jb.CouponSystemProjectP2.Exceptions.CompanyNotFoundException;
import com.jb.CouponSystemProjectP2.Exceptions.CustomerException;
import com.jb.CouponSystemProjectP2.Exceptions.CustomerNotFoundException;
import com.jb.CouponSystemProjectP2.Services.AdministratorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdministratorController {
    private final AdministratorService couponService;

    //           --------------------GET------------------
    @GetMapping("/get-companies")
    public ResponseEntity<?> getAllCompanies() throws CompanyException, CompanyNotFoundException {
        return new ResponseEntity<>(couponService.readAllCompanies(), HttpStatus.OK);
    }

    @GetMapping("/get-customers")
    public ResponseEntity<?> getAllCustomers() throws CompanyException, CustomerException, CustomerNotFoundException {
        return new ResponseEntity<>(couponService.readAllCustomers(), HttpStatus.OK);
    }

    @GetMapping("/company/{id}")
    public ResponseEntity<?> getCompanyById(@PathVariable int id) throws CompanyException, CompanyNotFoundException {
        return new ResponseEntity<>(couponService.readCompanyById(id), HttpStatus.OK);
    }

    @GetMapping("/customer/{id}")
    public ResponseEntity<?> getCustomerById(@PathVariable int id) throws CustomerException, CustomerNotFoundException {
        return new ResponseEntity<>(couponService.readCustomerById(id), HttpStatus.OK);
    }

    //          -------------------CREATE-------------------
    @PostMapping("/add/company")
    public ResponseEntity<?> createNewCompany(@RequestBody Company company) throws CompanyException {
        couponService.createCompany(company);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }


    @PostMapping("/add/customer")
    public ResponseEntity<?> createNewCustomer(@RequestBody Customer customer) throws CustomerException {
        couponService.createCustomer(customer);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    //          -------------------UPDATE----------------------
    @PutMapping("/update-company")
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    public void updateCompany(@RequestBody Company company) throws CompanyNotFoundException {
        couponService.updateCompany(company);
    }

    @PutMapping("/update-customer")
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    public void updateCustomer(@RequestBody Customer customer) throws CustomerNotFoundException {
        couponService.updateCustomer(customer);
    }

    //              -----------------DELETE------------------
    @DeleteMapping("/delete-company/{id}")
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    public void deleteCompany(@PathVariable int id) throws CompanyNotFoundException {
        couponService.deleteCompanyById(id);
    }

    @DeleteMapping("/delete-customer/{id}")
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    public void deleteCustomer(@PathVariable int id) throws CustomerNotFoundException {
        couponService.deleteCustomerById(id);
    }
}
