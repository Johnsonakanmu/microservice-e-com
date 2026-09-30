package com.ecommerce.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserRequest {

    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private AddressDto address;
}
