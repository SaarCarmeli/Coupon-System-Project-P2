package com.jb.CouponSystemProjectP2.Advices;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Java class for containing error titles and descriptions for use by advice classes
 */
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ErrorDetails {
    private String error, description;
}
