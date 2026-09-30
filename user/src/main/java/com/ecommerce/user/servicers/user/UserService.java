package com.ecommerce.user.servicers.user;

import com.ecommerce.user.dto.UserRequest;
import com.ecommerce.user.dto.UserResponse;

import java.util.List;
import java.util.Optional;

public interface UserService {

    public List<UserResponse> getAllUsers();

    public Optional<UserResponse> getUser(Long id);

    public UserResponse addUser(UserRequest userRequest);

    public UserResponse updateUser(Long id, UserRequest userRequest);
}
