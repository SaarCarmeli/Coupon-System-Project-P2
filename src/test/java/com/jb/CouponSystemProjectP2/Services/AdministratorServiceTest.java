package com.jb.CouponSystemProjectP2.Services;

import com.jb.CouponSystemProjectP2.Beans.Company;
import com.jb.CouponSystemProjectP2.Exceptions.CompanyException;
import com.jb.CouponSystemProjectP2.Exceptions.CompanyNotFoundException;
import com.jb.CouponSystemProjectP2.Repositories.CompanyRepository;
import com.jb.CouponSystemProjectP2.Repositories.CustomerRepository;
import com.jb.CouponSystemProjectP2.Util.TablePrinter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;



import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


class AdministratorServiceTest extends BaseServiceTest {

    @Mock
    private CompanyRepository companyRepository;
    @Mock
    private CustomerRepository customerRepository;
    @InjectMocks
    private AdministratorService underTest;


    @Test
    void createCompany() throws CompanyException, CompanyNotFoundException {
        //given
        Company mockCompany = Company.builder()
                .name("Amazon")
                .email("mockCompany@mockCompany.com")
                .password("jungle")
                .build();
        when(companyRepository.existsByNameOrEmail(any(),any())).thenReturn(false);
        //what we test
        underTest.createCompany(mockCompany);
        verify(companyRepository,times(1)).existsByNameOrEmail(mockCompany.getName(),mockCompany.getEmail());
        verify(companyRepository,times(1)).save(mockCompany);
    }

    @Test
    void failedToCreateCompany() throws CompanyException, CompanyNotFoundException {
        //given
        Company mockCompany = Company.builder()
                .name("Amazon")
                .email("mockCompany@mockCompany.com")
                .password("jungle")
                .build();
        when(companyRepository.existsByNameOrEmail(any(),any())).thenReturn(true);
        //what we test
        Assertions.assertThrows(CompanyException.class,
                ()->underTest.createCompany(mockCompany));
        verify(companyRepository,times(1)).existsByNameOrEmail(mockCompany.getName(),mockCompany.getEmail());
        verify(companyRepository,times(0)).save(mockCompany);
    }
    @Test
    void readCompanyById() {
    }

    @Test
    void canReadAllCompanies() throws CompanyNotFoundException {
        //when
        String url = "http://localhost:8080/admin/all/companies";
        //then

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