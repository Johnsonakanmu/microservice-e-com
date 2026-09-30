package com.ecommerce.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AddressDto {

    private Long id;
    private String street;
    private String city;
    private String state;
    private String country;
    private String zidCode;
}
