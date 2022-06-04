package com.jb.CouponSystemProjectP2.Beans;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

/**
 * A singleton entity that exists for the purpose of temporarily holding the logging information of a user while logging in to the system.
 * It is also used in the creation of a JSON Web Token (JWT) that is used for authentication of the logged user for use of the system.
 * It holds the UserType (enum), email, password and ID number if the user is not an admin
 */
@Component
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class LoginDetails {
    private int id;
    private String email, password;
    private UserType userType;

    /**
     * A constructor that builds an instance of LoginDetails without an ID parameter.
     *
     * @param email    User email
     * @param password User password
     * @param userType User type either ADMIN, COMPANY or CUSTOMER
     */
    public LoginDetails(String email, String password, UserType userType) {
        this.id = 0;
        this.email = email;
        this.password = password;
        this.userType = userType;
    }
}
