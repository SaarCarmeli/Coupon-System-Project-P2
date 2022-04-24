package com.jb.CouponSystemProjectP2.Services;

import com.jb.CouponSystemProjectP2.Beans.Company;
import com.jb.CouponSystemProjectP2.Repositories.CompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CompanyService {
    private final CompanyRepository companyRepository;


    public void createCompany(Company company) {
        companyRepository.save(company);
    }

    public Company readCompanyById(int id) {
        Optional<Company> company = companyRepository.findById(id);
        return company.orElse(null);
    }

    public List<Company> readAllCompanies() {
        return companyRepository.findAll();
    }

    public void updateCompany(Company company) {
        if (companyRepository.existsById(company.getId())) {
            companyRepository.save(company);
        } else {
            System.out.println("Coupon not found");  // todo: customize exception.
        }
    }

    public void deleteCompany(int id) {
        companyRepository.deleteById(id);
    }

    public boolean isCompanyExists(int id) {
        return companyRepository.existsById(id);
    }
}
