package com.dailyTracker.habit_tracker.controller;


import com.dailyTracker.habit_tracker.dto.UpdateUserRequest;
import com.dailyTracker.habit_tracker.dto.UserResponse;
import com.dailyTracker.habit_tracker.model.User;
import com.dailyTracker.habit_tracker.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.dailyTracker.habit_tracker.Constants.BEARER_PREFIX_LENGTH;


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
@GetMapping("/me")
    private ResponseEntity<UserResponse> loadUserData(@RequestHeader("Authorization") String authHeader) throws Exception {
    Long userId = userService.getUserIdFromToken(authHeader.substring(BEARER_PREFIX_LENGTH)).orElseThrow(() ->new Exception("Invalid Token"));
    User userToFind = userService.findById(userId).orElseThrow(() ->new Exception("No user found"));
    return ResponseEntity.ok(new UserResponse(userId,userToFind.getUserName(),userToFind.getEmail(),userToFind.getCreatedAt())) ;
}
@PatchMapping("/me")
    private ResponseEntity<UpdateUserRequest> updateUserStartingWeightAndTargetWeight(
            @RequestHeader("Authorization") String authHeader,@RequestBody UpdateUserRequest updateUserRequest)throws Exception{
    Long userId = userService.getUserIdFromToken(authHeader.substring(BEARER_PREFIX_LENGTH)).orElseThrow(() ->new Exception("Invalid Token"));
    userService.updateUser(userId,updateUserRequest.getStartWeight(),updateUserRequest.getTargetWeight());
    return ResponseEntity.noContent().build();
}
}

