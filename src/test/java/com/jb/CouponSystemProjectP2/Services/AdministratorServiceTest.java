package com.jb.CouponSystemProjectP2.Services;

import com.jb.CouponSystemProjectP2.Beans.Company;
import com.jb.CouponSystemProjectP2.Exceptions.CompanyException;
import com.jb.CouponSystemProjectP2.Exceptions.CompanyNotFoundException;
import com.jb.CouponSystemProjectP2.Repositories.CompanyRepository;
import com.jb.CouponSystemProjectP2.Repositories.CustomerRepository;
import com.jb.CouponSystemProjectP2.Util.TablePrinter;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;


class AdministratorServiceTest {

    @Mock
    private CompanyRepository companyRepository;
    private CustomerRepository customerRepository;
    private AdministratorService underTest;
    private AutoCloseable autoCloseable;

    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        underTest = new AdministratorService(companyRepository, customerRepository);
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void createCompany() throws CompanyException, CompanyNotFoundException {
        //given
        Company amazon = Company.builder()
                .id(1)
                .name("Amazon")
                .email("amazon@amazon.com")
                .password("jungle")
                .build();
        underTest.createCompany(amazon);
        TablePrinter.print(amazon);

        //when
        Company mockCompany = underTest.readCompanyById(amazon.getId());
        //then
        assertThat(amazon).isEqualTo(mockCompany);
    }

    @Test
    void readCompanyById() {
    }

    @Test
    void canReadAllCompanies() throws CompanyNotFoundException {
        //when
        underTest.readAllCompanies();
        //then
        verify(companyRepository).findAll();
    }

    @Test
    void updateCompany() {
    }

    @Test
    void deleteCompanyById() {
    }

    @Test
    void createCustomer() {
    }

    @Test
    void readCustomerById() {
    }

    @Test
    void readAllCustomers() {
    }

    @Test
    void updateCustomer() {
    }

    @Test
    void deleteCustomerById() {
    }
}