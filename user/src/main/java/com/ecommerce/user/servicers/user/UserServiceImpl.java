package com.ecommerce.user.servicers.user;

import com.ecommerce.user.dto.UserRequest;
import com.ecommerce.user.dto.UserResponse;
import com.ecommerce.user.mapper.UserMapper;
import com.ecommerce.user.repository.UserRepository;
import com.ecommerce.user.model.Address;
import com.ecommerce.user.model.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private UserMapper userMapper;
    @Override
    public List<UserResponse> getAllUsers() {

        return userRepository.findAll().stream()
                .map(userMapper::mapToUserResponse)
                .toList();
    }

    @Override
    public Optional<UserResponse> getUser(Long id) {
        return userRepository.findById(id)
                .map(userMapper::mapToUserResponse);
    }

    @Override
    public UserResponse addUser(UserRequest userRequest) {

        User user = userMapper.mapToUser(userRequest);

        User savedUser = userRepository.save(user);

        return userMapper.mapToUserResponse(savedUser);
    }

    @Override
    public UserResponse updateUser(Long id, UserRequest userRequest) {

        User existingUser = userRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("User not found with id: " + id)
                );

        if (userRequest.getFirstName() != null) {
            existingUser.setFirstName(userRequest.getFirstName());
        }

        if (userRequest.getLastName() != null) {
            existingUser.setLastName(userRequest.getLastName());
        }

        if (userRequest.getEmail() != null) {
            existingUser.setEmail(userRequest.getEmail());
        }

        if (userRequest.getPhoneNumber() != null) {
            existingUser.setPhoneNumber(userRequest.getPhoneNumber());
        }

        if (userRequest.getAddress() != null) {

            if (existingUser.getAddress() == null) {
                Address address = new Address();

                address.setStreet(userRequest.getAddress().getStreet());
                address.setCity(userRequest.getAddress().getCity());
                address.setState(userRequest.getAddress().getState());
                address.setCountry(userRequest.getAddress().getCountry());
                address.setZidCode(userRequest.getAddress().getZidCode());

                existingUser.setAddress(address);

            } else {

                if (userRequest.getAddress().getStreet() != null) {
                    existingUser.getAddress()
                            .setStreet(userRequest.getAddress().getStreet());
                }

                if (userRequest.getAddress().getCity() != null) {
                    existingUser.getAddress()
                            .setCity(userRequest.getAddress().getCity());
                }

                if (userRequest.getAddress().getState() != null) {
                    existingUser.getAddress()
                            .setState(userRequest.getAddress().getState());
                }

                if (userRequest.getAddress().getCountry() != null) {
                    existingUser.getAddress()
                            .setCountry(userRequest.getAddress().getCountry());
                }

                if (userRequest.getAddress().getZidCode() != null) {
                    existingUser.getAddress()
                            .setZidCode(userRequest.getAddress().getZidCode());
                }
            }
        }

        User updatedUser = userRepository.save(existingUser);

        return userMapper.mapToUserResponse(updatedUser);
    }


}
