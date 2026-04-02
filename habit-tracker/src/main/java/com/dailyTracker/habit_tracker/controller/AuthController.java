package com.dailyTracker.habit_tracker.controller;

import com.dailyTracker.habit_tracker.dto.AuthResponse;
import com.dailyTracker.habit_tracker.dto.LoginRequest;
import com.dailyTracker.habit_tracker.dto.RegisterRequest;
import com.dailyTracker.habit_tracker.model.User;
import com.dailyTracker.habit_tracker.security.JwUtil;
import com.dailyTracker.habit_tracker.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;
    private final JwUtil jwUtil;
    private final PasswordEncoder passwordEncoder;
    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request){
        User user = new User();
        user.setUserName(request.getUserName());
        user.setEmail(request.getEmail());
        user.setPasswordHash( passwordEncoder.encode(request.getPassword()));

        User saved = userService.save(user);
        String token = jwUtil.generateToken(saved.getUserName());



        return ResponseEntity.status(201).body(new AuthResponse(token));
    }
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request){
        Optional<User> userToFind = userService.findByEmail(request.getEmail());

        if(userToFind.isPresent() && passwordEncoder.matches(request.getPassword(),userToFind.get().getPasswordHash())){
            String token = jwUtil.generateToken(userToFind.get().getUserName());
            return ResponseEntity.status(200).body(new AuthResponse(token));
        }

        return ResponseEntity.notFound().build();
    }
}
