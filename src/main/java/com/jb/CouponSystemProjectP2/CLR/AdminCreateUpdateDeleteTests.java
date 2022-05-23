package com.jb.CouponSystemProjectP2.CLR;


import com.jb.CouponSystemProjectP2.Beans.Company;
import com.jb.CouponSystemProjectP2.Exceptions.CompanyException;
import com.jb.CouponSystemProjectP2.Services.AdministratorService;
import com.jb.CouponSystemProjectP2.Util.TablePrinter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(3)
@RequiredArgsConstructor
public class AdminCreateUpdateDeleteTests implements CommandLineRunner {
    AdministratorService administratorService;

    @Override
    public void run(String... args) throws Exception {
        //@CreateCompanyTest
        System.out.println("Check if a company is created");
        Company company = Company.builder()
                .name("google")
                .email("google@google.com")
                .password("jungle")
                .build();
        administratorService.createCompany(company);
        TablePrinter.print(administratorService.readCompanyById(company.getId()));
        System.out.println("Company has been successfully created");
        System.out.println("====================================================");
        System.out.println();


        System.out.println("Check if a company is not created");
        Company company1 = Company.builder()
                .name("google")
                .email("google@google.com")
                .password("jungle")
                .build();
       try {
           administratorService.createCompany(company);
           TablePrinter.print(administratorService.readCompanyById(1));
       } catch (CompanyException e){
           e.getMessage();
           System.out.println("Test Successfully Failed" );
       }
       System.out.println();
        System.out.println("====================================================");
        System.out.println();
    }
}
// void createCompany(Company company) throws CompanyException; V
//    Company readCompanyById(int id) throws CompanyNotFoundException;
//    List<Company> readAllCompanies() throws CompanyNotFoundException;
//    void updateCompany(Company company) throws CompanyNotFoundException;
//    void deleteCompanyById(int id) throws CompanyNotFoundException;
//
//    void createCustomer(Customer customer) throws CustomerException;
//    Customer readCustomerById(int id) throws CustomerNotFoundException;
//    List<Customer> readAllCustomers() throws CustomerNotFoundException;
//    void updateCustomer(Customer customer) throws CustomerNotFoundException;
//    void deleteCustomerById(int id) throws CustomerNotFoundException;
//}
