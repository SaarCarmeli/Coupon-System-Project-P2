package com.jb.CouponSystemProjectP2.Controllers;

import com.jb.CouponSystemProjectP2.Beans.Company;
import com.jb.CouponSystemProjectP2.Beans.Customer;
import com.jb.CouponSystemProjectP2.Beans.UserType;
import com.jb.CouponSystemProjectP2.Exceptions.*;
import com.jb.CouponSystemProjectP2.Security.JWTutil;
import com.jb.CouponSystemProjectP2.Services.AdministratorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


/**
 * RESTful web service class (RestController) for Administrator client type.
 */
@RestController
@RequestMapping("/admin") // http://localhost:8080/admin
@RequiredArgsConstructor
public class AdministratorController {
    private final AdministratorService administratorService;
    private final JWTutil jwtUtil;

    /**
     * A method that returns all Companies in the database.
     *
     * @param token Valid, logged Administrator's JSON Web Token (JWT)
     * @return New token, 200-Ok HTTP status and a List of all Companies
     * @throws CompanyNotFoundException  Thrown if the List is empty (no Companies in the database)
     * @throws UnauthorizedUserException Thrown if a client type other than Administrator attempts to use this method
     */
    @GetMapping("/all/companies") // http://localhost:8080/admin/all/companies
    public ResponseEntity<?> getAllCompanies(@RequestHeader(name = "Authorization") String token) throws CompanyNotFoundException, UnauthorizedUserException {
        if (!jwtUtil.getUserTypeFromToken(token).equals(UserType.ADMIN)) {
            throw new UnauthorizedUserException();
        }
        return ResponseEntity.ok()
                .header("Authorization", jwtUtil.generateToken(token))
                .body(administratorService.readAllCompanies());
    }

    /**
     * A method that returns all Customers in the database.
     *
     * @param token Valid, logged Administrator's JSON Web Token (JWT)
     * @return New token, 200-Ok HTTP status and a List of all Customers
     * @throws CustomerNotFoundException Thrown if the List is empty (no Customers in the database)
     * @throws UnauthorizedUserException Thrown if a client type other than Administrator attempts to use this method
     */
    @GetMapping("/all/customers") // http://localhost:8080/admin/all/customers
    public ResponseEntity<?> getAllCustomers(@RequestHeader(name = "Authorization") String token) throws CustomerNotFoundException, UnauthorizedUserException {
        if (!jwtUtil.getUserTypeFromToken(token).equals(UserType.ADMIN)) {
            throw new UnauthorizedUserException();
        }
        return ResponseEntity.ok()
                .header("Authorization", jwtUtil.generateToken(token))
                .body(administratorService.readAllCustomers());
    }

    /**
     * A method that returns any Company from the database by ID number.
     *
     * @param token Valid, logged Administrator's JSON Web Token (JWT)
     * @param id    Company ID number
     * @return New token, 200-Ok HTTP status and a Company entity
     * @throws CompanyNotFoundException  Thrown if a Company by that ID number is not found
     * @throws UnauthorizedUserException Thrown if a client type other than Administrator attempts to use this method
     */
    @GetMapping("/company/{id}") // http://localhost:8080/admin/company/{id}
    public ResponseEntity<?> getCompanyById(@RequestHeader(name = "Authorization") String token, @PathVariable int id) throws CompanyNotFoundException, UnauthorizedUserException {
        if (!jwtUtil.getUserTypeFromToken(token).equals(UserType.ADMIN)) {
            throw new UnauthorizedUserException();
        }
        return ResponseEntity.ok()
                .header("Authorization", jwtUtil.generateToken(token))
                .body(administratorService.readCompanyById(id));
    }

    /**
     * A method that returns any Customer from the database by ID number.
     *
     * @param token Valid, logged Administrator's JSON Web Token (JWT)
     * @param id    Customer ID number
     * @return New token, 200-Ok HTTP status and a Customer entity
     * @throws CustomerNotFoundException Thrown if a Customer by that ID number is not found
     * @throws UnauthorizedUserException Thrown if a client type other than Administrator attempts to use this method
     */
    @GetMapping("/customer/{id}") // http://localhost:8080/admin/customer/{id}
    public ResponseEntity<?> getCustomerById(@RequestHeader(name = "Authorization") String token, @PathVariable int id) throws CustomerNotFoundException, UnauthorizedUserException {
        if (!jwtUtil.getUserTypeFromToken(token).equals(UserType.ADMIN)) {
            throw new UnauthorizedUserException();
        }
        return ResponseEntity.ok()
                .header("Authorization", jwtUtil.generateToken(token))
                .body(administratorService.readCustomerById(id));
    }

    /**
     * A method to create a new Company entity.
     *
     * @param token   Valid, logged Administrator's JSON Web Token (JWT)
     * @param company new Company to add to the database
     * @return New token and 202-Accepted HTTP status
     * @throws CompanyException          Thrown if a Company with the new Company's name or email already exists
     * @throws UnauthorizedUserException Thrown if a client type other than Administrator attempts to use this method
     */
    @PostMapping("/add/company") // http://localhost:8080/admin/add/company
    public ResponseEntity<?> createNewCompany(@RequestHeader(name = "Authorization") String token, @RequestBody Company company) throws CompanyException, UnauthorizedUserException {
        if (!jwtUtil.getUserTypeFromToken(token).equals(UserType.ADMIN)) {
            throw new UnauthorizedUserException();
        }
        administratorService.createCompany(company);
        return ResponseEntity.accepted()
                .header("Authorization", jwtUtil.generateToken(token))
                .build();
    }

    /**
     * A method to create a new Customer entity.
     *
     * @param token    Valid, logged Administrator's JSON Web Token (JWT)
     * @param customer new Customer to add to the database
     * @return New token and 202-Accepted HTTP status
     * @throws CustomerException         Thrown if a Customer with the new Customer's email already exists
     * @throws UnauthorizedUserException Thrown if a client type other than Administrator attempts to use this method
     */
    @PostMapping("/add/customer") // http://localhost:8080/admin/add/customer
    public ResponseEntity<?> createNewCustomer(@RequestHeader(name = "Authorization") String token, @RequestBody Customer customer) throws CustomerException, UnauthorizedUserException {
        if (!jwtUtil.getUserTypeFromToken(token).equals(UserType.ADMIN)) {
            throw new UnauthorizedUserException();
        }
        administratorService.createCustomer(customer);
        return ResponseEntity.accepted()
                .header("Authorization", jwtUtil.generateToken(token))
                .build();
    }

    /**
     * A method to update a Company entity.
     *
     * @param token   Valid, logged Administrator's JSON Web Token (JWT)
     * @param company Company entity for update
     * @return New token and 202-Accepted HTTP status
     * @throws CompanyNotFoundException  Thrown if a Company by the argument Company's ID number is not found
     * @throws UnauthorizedUserException Thrown if a client type other than Administrator attempts to use this method
     * @throws CompanyException          Thrown if a Company with the update-company's email already exists
     */
    @PutMapping("/update/company") // http://localhost:8080/admin/update/company
    public ResponseEntity<?> updateCompany(@RequestHeader(name = "Authorization") String token, @RequestBody Company company) throws CompanyNotFoundException, UnauthorizedUserException, CompanyException {
        if (!jwtUtil.getUserTypeFromToken(token).equals(UserType.ADMIN)) {
            throw new UnauthorizedUserException();
        }
        administratorService.updateCompany(company);
        return ResponseEntity.accepted()
                .header("Authorization", jwtUtil.generateToken(token))
                .build();
    }

    /**
     * A method to update a Customer entity.
     *
     * @param token    Valid, logged Administrator's JSON Web Token (JWT)
     * @param customer Customer entity for update
     * @return New token and 202-Accepted HTTP status
     * @throws CustomerNotFoundException Thrown if a Customer by the argument Customer's ID number is not found
     * @throws UnauthorizedUserException Thrown if a client type other than Administrator attempts to use this method
     * @throws CustomerException         Thrown if a Customer with the update-customer's email already exists
     */
    @PutMapping("/update/customer") // http://localhost:8080/admin/update/customer
    public ResponseEntity<?> updateCustomer(@RequestHeader(name = "Authorization") String token, @RequestBody Customer customer) throws CustomerNotFoundException, UnauthorizedUserException, CustomerException {
        if (!jwtUtil.getUserTypeFromToken(token).equals(UserType.ADMIN)) {
            throw new UnauthorizedUserException();
        }
        administratorService.updateCustomer(customer);
        return ResponseEntity.accepted()
                .header("Authorization", jwtUtil.generateToken(token))
                .build();
    }

    /**
     * A method to delete a Company entity.
     *
     * @param token Valid, logged Administrator's JSON Web Token (JWT)
     * @param id    ID number of the Company to be deleted
     * @return New token and 202-Accepted HTTP status
     * @throws CompanyNotFoundException  Thrown if a Company by that ID number is not found
     * @throws UnauthorizedUserException Thrown if a client type other than Administrator attempts to use this method
     */
    @DeleteMapping("/delete/company/{id}") // http://localhost:8080/admin/delete/company/{id}
    public ResponseEntity<?> deleteCompany(@RequestHeader(name = "Authorization") String token, @PathVariable int id) throws CompanyNotFoundException, UnauthorizedUserException {
        if (!jwtUtil.getUserTypeFromToken(token).equals(UserType.ADMIN)) {
            throw new UnauthorizedUserException();
        }
        administratorService.deleteCompanyById(id);
        return ResponseEntity.accepted()
                .header("Authorization", jwtUtil.generateToken(token))
                .build();
    }

    /**
     * A method to delete a Customer entity.
     *
     * @param token Valid, logged Administrator's JSON Web Token (JWT)
     * @param id    ID number of the Customer to be deleted
     * @return New token and 202-Accepted HTTP status
     * @throws CustomerNotFoundException Thrown if a Customer by that ID number is not found
     * @throws UnauthorizedUserException Thrown if a client type other than Administrator attempts to use this method
     */
    @DeleteMapping("/delete/customer/{id}") // http://localhost:8080/admin/delete/customer/{id}
    public ResponseEntity<?> deleteCustomer(@RequestHeader(name = "Authorization") String token, @PathVariable int id) throws CustomerNotFoundException, UnauthorizedUserException {
        if (!jwtUtil.getUserTypeFromToken(token).equals(UserType.ADMIN)) {
            throw new UnauthorizedUserException();
        }
        administratorService.deleteCustomerById(id);
        return ResponseEntity.accepted()
                .header("Authorization", jwtUtil.generateToken(token))
                .build();
    }
}
