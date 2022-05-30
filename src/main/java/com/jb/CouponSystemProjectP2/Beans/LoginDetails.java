package com.jb.CouponSystemProjectP2.Beans;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class LoginDetails {
    private int id;
    private String email, password;
    private UserType userType;

    public LoginDetails(String email, String password, UserType userType) {
        this.id = 0;
        this.email = email;
        this.password = password;
        this.userType = userType;
    }
}
