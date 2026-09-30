package com.ecommerce.user.mapper;

import com.ecommerce.user.dto.AddressDto;
import com.ecommerce.user.dto.UserRequest;
import com.ecommerce.user.dto.UserResponse;
import com.ecommerce.user.model.Address;
import com.ecommerce.user.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public User mapToUser(UserRequest userRequest) {

        User user = new User();

        user.setFirstName(userRequest.getFirstName());
        user.setLastName(userRequest.getLastName());
        user.setEmail(userRequest.getEmail());
        user.setPhoneNumber(userRequest.getPhoneNumber());

        if (userRequest.getAddress() != null) {

            Address address = new Address();

            address.setStreet(userRequest.getAddress().getStreet());
            address.setCity(userRequest.getAddress().getCity());
            address.setState(userRequest.getAddress().getState());
            address.setCountry(userRequest.getAddress().getCountry());
            address.setZidCode(userRequest.getAddress().getZidCode());

            user.setAddress(address);
        }

        return user;
    }

    public UserResponse mapToUserResponse(User user) {
        UserResponse response = new UserResponse();

        response.setId(String.valueOf(user.getId()));
        response.setFirstName(user.getFirstName());
        response.setLastName(user.getLastName());
        response.setEmail(user.getEmail());
        response.setPhoneNumber(user.getPhoneNumber());
        response.setRole(user.getRole());
        if (user.getAddress() != null) {
            AddressDto addressDto = new AddressDto();
            addressDto.setId(user.getId());
            addressDto.setStreet(user.getAddress().getStreet());
            addressDto.setCity(user.getAddress().getCity());
            addressDto.setState(user.getAddress().getState());
            addressDto.setCountry(user.getAddress().getCountry());
            addressDto.setZidCode(user.getAddress().getZidCode());
            response.setAddress(addressDto);
        }
        return response;
    }

}
