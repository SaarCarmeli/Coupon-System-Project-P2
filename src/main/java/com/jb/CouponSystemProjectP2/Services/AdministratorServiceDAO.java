package com.jb.CouponSystemProjectP2.Services;

import com.jb.CouponSystemProjectP2.Beans.Company;
import com.jb.CouponSystemProjectP2.Beans.Customer;
import com.jb.CouponSystemProjectP2.Exceptions.CompanyException;
import com.jb.CouponSystemProjectP2.Exceptions.CustomerException;

import java.util.List;

public interface AdministratorServiceDAO {
    void createCompany(Company company) throws CompanyException;
    Company readCompanyById(int id) throws CompanyException;
    List<Company> readAllCompanies() throws CompanyException;
    void updateCompany(Company company) throws CompanyException;
    void deleteCompanyById(int id) throws CompanyException;

    void createCustomer(Customer customer) throws CompanyException;
    Customer readCustomerById(int id) throws CustomerException;
    List<Customer> readAllCustomers() throws CustomerException;
    void updateCustomer(Customer customer) throws CustomerException;
    void deleteCustomerById(int id) throws CustomerException;
}
