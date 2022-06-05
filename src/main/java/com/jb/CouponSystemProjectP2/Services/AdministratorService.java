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

/**
 * A service class implementing all the methods available for Administrator user.
 */
@Service
@RequiredArgsConstructor
public class AdministratorService implements AdministratorServiceDAO {
    private final CompanyRepository companyRepository;
    private final CustomerRepository customerRepository;

    /**
     * A method to create a new Company entity.
     *
     * @param company new Company to add to the database
     * @throws CompanyException Thrown if a Company with the new Company's name or email already exists
     */
    @Override
    public void createCompany(Company company) throws CompanyException {
        if (companyRepository.existsByNameOrEmail(company.getName(), company.getEmail())) {
            throw new CompanyException(
                    "Failed to create 'company', as 'company' by name= " + company.getName() + ", or by email= " + company.getEmail() + " already exists!");
        }
        companyRepository.save(company);
    }

    /**
     * A method that returns any Company from the database by ID number.
     *
     * @param id Company ID number
     * @return Company entity
     * @throws CompanyNotFoundException Thrown if a Company by that ID number is not found
     */
    @Override
    public Company readCompanyById(int id) throws CompanyNotFoundException {
        Optional<Company> companyOpt = companyRepository.findById(id);
        if (companyOpt.isPresent()) {
            return companyOpt.get();
        }
        throw new CompanyNotFoundException("Failed to read 'company', as 'company' by ID= " + id + " does not exist!");
    }

    /**
     * A method that returns all Companies in the database.
     *
     * @return List of all Companies
     * @throws CompanyNotFoundException Thrown if the List is empty (no Companies in the database)
     */
    @Override
    public List<Company> readAllCompanies() throws CompanyNotFoundException {
        List<Company> companyList = companyRepository.findAll();
        if (!companyList.isEmpty()) {
            return companyList;
        }
        throw new CompanyNotFoundException("Failed to read 'companies' , as there no 'companies' in the database!");
    }

    /**
     * A method to update a Company entity.
     *
     * @param company Company entity for update
     * @throws CompanyNotFoundException Thrown if a Company by the argument Company's ID number is not found
     * @throws CompanyException         Thrown if another Company with the update-company's email already exists
     */
    @Override
    public void updateCompany(Company company) throws CompanyNotFoundException, CompanyException {
        if (companyRepository.existsById(company.getId())) {
            if (!companyRepository.existsByEmailAndIdNot(company.getEmail(), company.getId())) {
                companyRepository.save(company);
            } else {
                throw new CompanyException("Failed to update 'company', as another 'company' by email= " + company.getEmail() + " already exists!");
            }
        } else {
            throw new CompanyNotFoundException("Failed to update 'company', as 'company' by ID= " + company.getId() + " does not exist!");
        }
    }

    /**
     * A method to delete a Company entity.
     *
     * @param id ID number of the Company to be deleted
     * @throws CompanyNotFoundException Thrown if a Company by that ID number is not found
     */
    @Override
    public void deleteCompanyById(int id) throws CompanyNotFoundException {
        if (companyRepository.existsById(id)) {
            companyRepository.deleteById(id);
        } else {
            throw new CompanyNotFoundException("Failed to delete 'company', as 'company' by ID= " + id + " does not exist!");
        }
    }

    /**
     * A method to create a new Customer entity.
     *
     * @param customer new Customer to add to the database
     * @throws CustomerException Thrown if a Customer with the new Customer's email already exists
     */
    @Override
    public void createCustomer(Customer customer) throws CustomerException {
        if (customerRepository.existsByEmail(customer.getEmail())) {
            throw new CustomerException("Failed to create 'customer', as 'customer' by email= " + customer.getEmail() + " already exists!");
        }
        customerRepository.save(customer);
    }

    /**
     * A method that returns any Customer from the database by ID number.
     *
     * @param id Customer ID number
     * @return Customer entity
     * @throws CustomerNotFoundException Thrown if a Customer by that ID number is not found
     */
    @Override
    public Customer readCustomerById(int id) throws CustomerNotFoundException {
        Optional<Customer> customerOpt = customerRepository.findById(id);
        if (customerOpt.isPresent()) {
            return customerOpt.get();
        }
        throw new CustomerNotFoundException("Failed to read 'customer', as 'customer' by ID= " + id + " does not exist!");
    }

    /**
     * A method that returns all Customers in the database.
     *
     * @return List of all Customers
     * @throws CustomerNotFoundException Thrown if the List is empty (no Customers in the database)
     */
    @Override
    public List<Customer> readAllCustomers() throws CustomerNotFoundException {
        List<Customer> customerList = customerRepository.findAll();
        if (!customerList.isEmpty()) {
            return customerList;
        }
        throw new CustomerNotFoundException("Failed to read 'customers' , as there no 'customers' in the database!");
    }

    /**
     * A method to update a Customer entity.
     *
     * @param customer Customer entity for update
     * @throws CustomerNotFoundException Thrown if a Customer by the argument Customer's ID number is not found
     * @throws CustomerException         Thrown if another Customer with the update-customer's email already exists
     */
    @Override
    public void updateCustomer(Customer customer) throws CustomerNotFoundException, CustomerException {
        if (customerRepository.existsById(customer.getId())) {
            if (!customerRepository.existsByEmailAndIdNot(customer.getEmail(), customer.getId())) {
                customerRepository.save(customer);
            } else {
                throw new CustomerException("Failed to update 'customer', as another 'customer' by email= " + customer.getEmail() + " already exists!");
            }
        } else {
            throw new CustomerNotFoundException("Failed to update 'customer', as 'customer' by ID= " + customer.getId() + " does not exist!");
        }
    }

    /**
     * A method to delete a Customer entity.
     *
     * @param id ID number of the Customer to be deleted
     * @throws CustomerNotFoundException Thrown if a Customer by that ID number is not found
     */
    @Override
    public void deleteCustomerById(int id) throws CustomerNotFoundException {
        if (customerRepository.existsById(id)) {
            customerRepository.deleteById(id);
        } else {
            throw new CustomerNotFoundException("Failed to delete 'customer', as 'customer' by ID= " + id + " does not exist!");
        }
    }
}