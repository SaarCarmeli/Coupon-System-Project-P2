package com.jb.CouponSystemProjectP2.Controller;

import com.jb.CouponSystemProjectP2.Beans.Company;
import com.jb.CouponSystemProjectP2.Service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/company")
@RequiredArgsConstructor
public class CompanyController {
    private final CompanyService companyService;

    @GetMapping("/get")
    public ResponseEntity<?> getAllCompanies() {
        return new ResponseEntity<>(companyService.readAllCompanies(), HttpStatus.OK);
    }

    @GetMapping("/companies/{id}")
    public ResponseEntity<?> getCompanyById(@PathVariable int id) {
        return new ResponseEntity<>(companyService.readCompanyById(id), HttpStatus.ACCEPTED);
    }

    @PostMapping("/add")
    public ResponseEntity<?> createNewCompany(@RequestBody Company company) {
        companyService.createCompany(company);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/update")
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    public void updateCompany(@RequestBody Company company) {
        try {
            companyService.updateCompany(company);
        } catch (Exception e) {
            System.out.println("Failed to update company"); // todo: customize exception.
        }
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    public void deleteCompany(@PathVariable int id) throws Exception {
        try {
            companyService.deleteCompany(id);
        } catch (Exception e) {
            System.out.println("Failed to delete company"); //todo:customize exception
        }
    }
}
