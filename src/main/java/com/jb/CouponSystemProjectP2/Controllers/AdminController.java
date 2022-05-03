package com.jb.CouponSystemProjectP2.Controllers;

import com.jb.CouponSystemProjectP2.Beans.Company;
import com.jb.CouponSystemProjectP2.Beans.Coupon;
import com.jb.CouponSystemProjectP2.Beans.Customer;
import com.jb.CouponSystemProjectP2.Exceptions.CompanyException;
import com.jb.CouponSystemProjectP2.Exceptions.CustomerException;
import com.jb.CouponSystemProjectP2.Services.AdministratorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/coupon")
@RequiredArgsConstructor
public class AdminController {
    private final AdministratorService couponService;

    //           --------------------GET------------------
    @GetMapping("/get-companies")
    public ResponseEntity<?> getAllCompanies() throws CompanyException {
        return new ResponseEntity<>(couponService.readAllCompanies(), HttpStatus.OK);
    }

    @GetMapping("/get-customers")
    public ResponseEntity<?> getAllCustomers() throws CompanyException, CustomerException {
        return new ResponseEntity<>(couponService.readAllCustomers(), HttpStatus.OK);
    }

    @GetMapping("/company/{id}")
    public ResponseEntity<?> getCompanyById(@PathVariable int id) throws CompanyException {
        return new ResponseEntity<>(couponService.readCompanyById(id), HttpStatus.ACCEPTED);
    }

    @GetMapping("/customer/{id}")
    public ResponseEntity<?> getCustomerById(@PathVariable int id) throws CustomerException {
        return new ResponseEntity<>(couponService.readCustomerById(id), HttpStatus.ACCEPTED);
    }

    //          -------------------CREATE-------------------
    @PostMapping("/add/company")
    public ResponseEntity<?> createNewCompany(@RequestBody Company company) throws CompanyException {
        couponService.createCompany(company);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }


    @PostMapping("/add/customer")
    public ResponseEntity<?> createNewCustomer(@RequestBody Customer customer) throws CompanyException {
        couponService.createCustomer(customer);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    //          -------------------UPDATE----------------------
    @PutMapping("/update-company")
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    public void updateCompany(@RequestBody Company company) throws CompanyException {
        couponService.updateCompany(company);
    }

    @PutMapping("/update-customer")
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    public void updateCustomer(@RequestBody Customer customer) throws CustomerException {
        couponService.updateCustomer(customer);
    }

    //              -----------------DELETE------------------
    @DeleteMapping("/delete-company/{id}")
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    public void deleteCompany(@PathVariable int id) throws CompanyException {
        couponService.deleteCompanyById(id);
    }

    @DeleteMapping("/delete-customer/{id}")
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    public void deleteCustomer(@PathVariable int id) throws CustomerException {
        couponService.deleteCustomerById(id);
    }
}
