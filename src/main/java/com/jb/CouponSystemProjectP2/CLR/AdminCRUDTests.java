package com.jb.CouponSystemProjectP2.CLR;


import com.jb.CouponSystemProjectP2.Beans.Company;
import com.jb.CouponSystemProjectP2.Beans.Customer;
import com.jb.CouponSystemProjectP2.Exceptions.CompanyException;
import com.jb.CouponSystemProjectP2.Exceptions.CompanyNotFoundException;
import com.jb.CouponSystemProjectP2.Exceptions.CustomerException;
import com.jb.CouponSystemProjectP2.Exceptions.CustomerNotFoundException;
import com.jb.CouponSystemProjectP2.Services.AdministratorService;
import com.jb.CouponSystemProjectP2.Util.TablePrinter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Order(2)
@RequiredArgsConstructor
public class AdminCRUDTests implements CommandLineRunner {
    private final AdministratorService administratorService;
    private final AdminTestMethods adminTestMethods;

    @Override
    public void run(String... args) throws Exception {
        //@createCompanyTest
        System.out.println("===============ADMIN SERVICE TEST===================== \n");
        System.out.println("1. Test if a company is created");
        Company company = Company.builder()
                .name("google")
                .email("google@google.com")
                .password("jungle")
                .build();
        administratorService.createCompany(company);
        TablePrinter.print(administratorService.readCompanyById(company.getId()));
        System.out.println("Company has been successfully created, test success");
        System.out.println("==================================================== \n");

        //@failedToCreateCompanyTest
        System.out.println("2. Test if a company is not created");
        Company company1 = Company.builder()
                .name("google")
                .email("google@google.com")
                .password("jungle")
                .build();
        try {
            administratorService.createCompany(company1);
            TablePrinter.print(administratorService.readCompanyById(1));
        } catch (CompanyException exception) {
            System.out.println(exception.getMessage());
            System.out.println("Test Successfully Failed \n");
        }
        System.out.println("==================================================== \n");

        //@UpdateCompanyTest
        System.out.println("3. Test if company " + company.getName() + " is updated");
        System.out.println();
        Company testCompany = Company.builder()
                .id(4)
                .name("Migdal")
                .email("Migdal@Migdal.com")
                .password("Migdal123")
                .build();

        administratorService.updateCompany(testCompany);
        TablePrinter.print(testCompany);
        System.out.println();
        System.out.println("Test successfully passed, company name has changed to -> " + testCompany.getName());
        System.out.println("====================================================");

        //@failedToUpdateCompanyTest
        System.out.println("4. Test if company " + testCompany.getName() + " is not updated");
        System.out.println();
        Company testCompany1 = Company.builder()
                .id(10)
                .name("update")
                .email("update@update.com")
                .password("update123")
                .build();
        try {
            administratorService.updateCompany(testCompany1);
            TablePrinter.print(testCompany1);
        } catch (CompanyNotFoundException exception) {
            System.out.println(exception.getMessage());
            System.out.println("Test successfully Failed");
        }
        //@deleteCompanyByIdTest
        System.out.println("====================================================");
        System.out.println("5. Test if company " + testCompany.getName() + " is deleted \n");
        administratorService.deleteCompanyById(4);
        try {
            TablePrinter.print(administratorService.readCompanyById(4));
        } catch (CompanyNotFoundException exception) {
            System.out.println(exception.getMessage());
            System.out.println("Test successfully passed");
        }
        //@failedToDeleteCompanyTest
        System.out.println("====================================================");
        System.out.println("6. Test if the company failed to delete");
        System.out.println();
        try {
            administratorService.deleteCompanyById(4);
            TablePrinter.print(testCompany);
        } catch (CompanyNotFoundException exception) {
            System.out.println(exception.getMessage());
            System.out.println("Test successfully Failed\n");

        }

        //@createCustomerTest
        System.out.println();
        System.out.println("7. Test if a customer is created");
        Customer customer = Customer.builder()
                .email("customer@test.com")
                .firstName("rebeca")
                .lastName("cohen")
                .password("test12345")
                .build();
        administratorService.createCustomer(customer);
        TablePrinter.print(administratorService.readCustomerById(customer.getId()));
        System.out.println("Customer has been successfully created, test success");
        System.out.println("==================================================== \n");

        //@FailedToCreateCustomerTest
        System.out.println("8. Test if a customer is not created");
        Customer customer1 = Customer.builder()
                .email("customer@test.com")
                .firstName("rebeca")
                .lastName("cohen")
                .password("test12345")
                .build();
        try {
            administratorService.createCustomer(customer1);
            TablePrinter.print(administratorService.readCustomerById(1));
        } catch (CustomerException exception) {
            System.out.println(exception.getMessage());
            System.out.println("Test Successfully Failed \n");
        }
        System.out.println("==================================================== \n");

        //@UpdateCustomerTest
        System.out.println("9. Test if customer " + customer.getFirstName() + " " + customer.getLastName() + " is updated");
        System.out.println();
        Customer testCustomer = Customer.builder()
                .id(3)
                .email("customer@test.com")
                .firstName("anna")
                .lastName("banana")
                .password("test12345")
                .build();

        administratorService.updateCustomer(testCustomer);
        TablePrinter.print(testCustomer);
        System.out.println();
        System.out.println("Test successfully passed, customer name has changed to -> " + testCustomer.getFirstName());
        System.out.println("==================================================== \n");

        //@failedToUpdateCustomerTest
        System.out.println("10. Test if customer is not updated , customer ID -> " + testCustomer.getId());
        System.out.println();
        Customer testCustomer1 = Customer.builder()
                .id(10)
                .email("customer@test.com")
                .firstName("customer")
                .lastName("test")
                .password("test12345")
                .build();
        try {
            administratorService.updateCustomer(testCustomer1);
            TablePrinter.print(testCustomer1);
        } catch (CustomerNotFoundException exception) {
            System.out.println(exception.getMessage());
            System.out.println("Test successfully Failed");
        }

        //@deleteCustomerByIdTest
        System.out.println("====================================================");
        System.out.println("11. Test if customer " + testCustomer.getFirstName() + " is deleted by the id = "
                + testCustomer.getId() + "\n");
        administratorService.deleteCustomerById(3);
        try {
            TablePrinter.print(administratorService.readCustomerById(3));
        } catch (CustomerNotFoundException exception) {
            System.out.println(exception.getMessage());
            System.out.println("Test successfully passed");
        }

        //@failedToDeleteCustomerTest
        System.out.println("====================================================");
        System.out.println("12. Test if the customer failed to delete");
        System.out.println();
        try {
            administratorService.deleteCustomerById(3);
            TablePrinter.print(testCompany);
        } catch (CustomerNotFoundException exception) {
            System.out.println(exception.getMessage());
            System.out.println("Test successfully Failed\n");
        }

        //@ReadCompanyByIdTest
        System.out.println("========================================== \n");
        System.out.println("13. Read company by id test");
        TablePrinter.print(administratorService.readCompanyById(3));
        System.out.println("Test successfully passed \n");

        //@FailedToReadCompanyTest
        System.out.println("==================================================== \n");
        System.out.println("14. Failed to read company by id test");
        try {
            TablePrinter.print(administratorService.readCompanyById(5));
        } catch (CompanyNotFoundException exception) {
            System.out.println(exception.getMessage());
            System.out.println("Test successfully Failed \n");
        }
        //@ReadAllCompaniesTest
        System.out.println("==================================================== \n");
        System.out.println("15. Read all companies test");
        List<Company> testCompanies = administratorService.readAllCompanies();
        TablePrinter.print(testCompanies);
        System.out.println("Test successfully passed \n");

        //@FailedToReadAllCompaniesTest
        System.out.println("==================================================== \n");
        System.out.println("16. Failed to read all companies test");
        testCompanies.forEach(item -> {
            try {
                administratorService.deleteCompanyById(item.getId());
            } catch (CompanyNotFoundException exception) { //
                System.out.println(exception.getMessage());
            }
        });
        adminTestMethods.clearAllCompaniesInTheDataBase();
        adminTestMethods.clearAllCouponsInTheDataBase();
        try {
            administratorService.readAllCompanies();
        } catch (CompanyNotFoundException exception) {
            System.out.println(exception.getMessage());
        }
        testCompanies.forEach(item -> {
            try {
                administratorService.createCompany(item);
            } catch (CompanyException exception) {
                System.out.println(exception.getMessage()+"\n");
            }
        });
                System.out.println("Test successfully Failed \n");

        //@ReadAllCustomersTest
        System.out.println("==================================================== \n");
        System.out.println("17. Read all customers test");
        List<Customer> testCustomers = administratorService.readAllCustomers();
        TablePrinter.print(testCustomers);
        System.out.println("Test successfully passed \n");

        //@FailedToReadAllCustomersTest
        System.out.println("==================================================== \n");
        System.out.println("18. Failed to read all customers test");
        testCustomers.forEach(item -> {
            try {
                administratorService.deleteCustomerById(item.getId());
            } catch (CustomerNotFoundException exception) {
                System.out.println(exception.getMessage());
            }
        });
        adminTestMethods.clearAllCustomersInTheDataBase();
        try {
            administratorService.readAllCustomers();
        } catch (CustomerNotFoundException exception) {
            System.out.println(exception.getMessage());
        }
        testCustomers.forEach(item -> {
            try {
                administratorService.createCustomer(item);
            } catch (CustomerException exception) {
                System.out.println(exception.getMessage());
            }
        });
                System.out.println("Test successfully Failed \n");
    }
}