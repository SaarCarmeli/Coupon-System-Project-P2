package com.jb.CouponSystemProjectP2.Services;

import com.jb.CouponSystemProjectP2.Beans.Company;
import com.jb.CouponSystemProjectP2.Beans.Customer;
import com.jb.CouponSystemProjectP2.Exceptions.CompanyException;
import com.jb.CouponSystemProjectP2.Exceptions.CompanyNotFoundException;
import com.jb.CouponSystemProjectP2.Exceptions.CustomerException;
import com.jb.CouponSystemProjectP2.Exceptions.CustomerNotFoundException;

import java.util.List;

public interface AdministratorServiceDAO {
    void createCompany(Company company) throws CompanyException;
    Company readCompanyById(int id) throws CompanyNotFoundException;
    List<Company> readAllCompanies() throws CompanyNotFoundException;
    void updateCompany(Company company) throws CompanyNotFoundException, CompanyException;
    void deleteCompanyById(int id) throws CompanyNotFoundException;

    void createCustomer(Customer customer) throws CustomerException;
    Customer readCustomerById(int id) throws CustomerNotFoundException;
    List<Customer> readAllCustomers() throws CustomerNotFoundException;
    void updateCustomer(Customer customer) throws CustomerNotFoundException, CustomerException;
    void deleteCustomerById(int id) throws CustomerNotFoundException;
}
