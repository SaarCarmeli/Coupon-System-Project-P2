package com.jb.CouponSystemProjectP2.Services;

import com.jb.CouponSystemProjectP2.Beans.Company;
import com.jb.CouponSystemProjectP2.Beans.Customer;
import com.jb.CouponSystemProjectP2.Exceptions.CompanyException;
import com.jb.CouponSystemProjectP2.Exceptions.CompanyNotFoundException;
import com.jb.CouponSystemProjectP2.Exceptions.CustomerException;
import com.jb.CouponSystemProjectP2.Exceptions.CustomerNotFoundException;
import com.jb.CouponSystemProjectP2.Repositories.CompanyRepository;
import com.jb.CouponSystemProjectP2.Repositories.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AdministratorService implements AdministratorServiceDAO {
    private final CompanyRepository companyRepository;
    private final CustomerRepository customerRepository;

    @Override
    public void createCompany(Company company) throws CompanyException {
        if (companyRepository.existsByNameOrEmail(company.getName(), company.getEmail())) {
            throw new CompanyException(
                    "Failed to create 'company', as 'company' by name= " + company.getName() + ", or by email= " + company.getEmail() + " already exists!");
        }
        companyRepository.save(company);
    }

    @Override
    public Company readCompanyById(int id) throws CompanyNotFoundException {
        Optional<Company> companyOpt = companyRepository.findById(id);
        if (companyOpt.isPresent()) {
            return companyOpt.get();
        }
        throw new CompanyNotFoundException("Failed to read 'company', as 'company' by ID= " + id + " does not exist!");
    }

    @Override
    public List<Company> readAllCompanies() throws CompanyNotFoundException {
        List<Company> companyList = companyRepository.findAll();
        if (!companyList.isEmpty()) {
            return companyList;
        }
        throw new CompanyNotFoundException("Failed to read 'companies', as there are no 'companies' in the database!");
    }

    @Override
    public void updateCompany(Company company) throws CompanyNotFoundException {
        // todo restrict update to company.id and company.name
        if (companyRepository.existsById(company.getId())) {
            companyRepository.save(company);
        } else {
            throw new CompanyNotFoundException("Failed to update 'company', as 'company' by ID= " + company.getId() + " does not exist!");
        }
    }

    @Override
    public void deleteCompanyById(int id) throws CompanyNotFoundException {
        if (companyRepository.existsById(id)) {
            companyRepository.deleteById(id);
        } else {
            throw new CompanyNotFoundException("Failed to delete 'company', as 'company' by ID= " + id + " does not exist!");
        }
    }

    @Override
    public void createCustomer(Customer customer) throws CustomerException {
        if (customerRepository.existsByEmail(customer.getEmail())) {
            throw new CustomerException("Failed to create 'customer', as 'customer' by email= " + customer.getEmail() + " already exists!");
        }
        customerRepository.save(customer);
    }

    @Override
    public Customer readCustomerById(int id) throws CustomerNotFoundException {
        Optional<Customer> customerOpt = customerRepository.findById(id);
        if (customerOpt.isPresent()) {
            return customerOpt.get();
        }
        throw new CustomerNotFoundException("Failed to read 'customer', as 'customer' by ID= " + id + " does not exist!");
    }

    @Override
    public List<Customer> readAllCustomers() throws CustomerNotFoundException {
        List<Customer> customerList = customerRepository.findAll();
        if (!customerList.isEmpty()) {
            return customerList;
        }
        throw new CustomerNotFoundException("Failed to read 'customers', as there are no 'customers' in the database!");
    }

    @Override
    public void updateCustomer(Customer customer) throws CustomerNotFoundException {
        // todo restrict update to customer.id
        if (customerRepository.existsById(customer.getId())) {
            customerRepository.save(customer);
        } else {
            throw new CustomerNotFoundException("Failed to update 'customer', as 'customer' by ID= " + customer.getId() + " does not exist!");
        }
    }

    @Override
    public void deleteCustomerById(int id) throws CustomerNotFoundException {
        if (customerRepository.existsById(id)) {
            customerRepository.deleteById(id);
        } else {
            throw new CustomerNotFoundException("Failed to delete 'customer', as 'customer' by ID= " + id + " does not exist!");
        }
    }
}