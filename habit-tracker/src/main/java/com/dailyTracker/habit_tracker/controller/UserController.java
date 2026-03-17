package com.dailyTracker.habit_tracker.controller;


import com.dailyTracker.habit_tracker.model.User;
import com.dailyTracker.habit_tracker.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RequiredArgsConstructor
@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

@GetMapping("/{id}")
    private ResponseEntity<User> getUserById(@PathVariable Long id) {
    return userService.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
}
@PostMapping
    private ResponseEntity<User> createUser(@RequestBody User user){
    //return ResponseEntity.created(URI).body(user);
    return ResponseEntity.status(201).body(userService.save(user));
}
}

