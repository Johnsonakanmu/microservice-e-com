package com.ecommerce.user.controller;

import com.ecommerce.user.dto.UserRequest;
import com.ecommerce.user.dto.UserResponse;
import com.ecommerce.user.servicers.user.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
@AllArgsConstructor
public class userController {

    private UserService userService;

    @GetMapping()
    public ResponseEntity<List<UserResponse>> getAllUsers() {

        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUser(@PathVariable() Long id) {

        return userService.getUser(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping()
    public ResponseEntity<UserResponse> addUser(@RequestBody UserRequest userRequest) {

        return ResponseEntity.ok(userService.addUser(userRequest));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponse> updateUser(@PathVariable() Long id, @RequestBody UserRequest userRequest) {
        return ResponseEntity.ok(
                userService.updateUser(id, userRequest)
        );
    }

}
