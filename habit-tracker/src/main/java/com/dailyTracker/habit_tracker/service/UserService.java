package com.dailyTracker.habit_tracker.service;

import com.dailyTracker.habit_tracker.model.User;
import com.dailyTracker.habit_tracker.repository.UserRepository;
import com.dailyTracker.habit_tracker.security.JwUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final JwUtil jwUtil;
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }
    public Optional<Long> getUserIdFromToken(String token) throws Exception {
        String userNameToExtract = jwUtil.extractUsername(token);
        if(jwUtil.isTokenValid(token,userNameToExtract))
            return userRepository.findByUserName(userNameToExtract).map(User::getId);

        throw new Exception("Invalid Token");
    }
    public User save(User user){
        return userRepository.save(user);
    }
    public User updateUser(Long userId, Double startWeight, Double targetWeight){
        User userToUpdate = userRepository.findById(userId).orElseThrow(()->new RuntimeException("User not found"));
        userToUpdate.setStartWeight(startWeight);
        userToUpdate.setTargetWeight(targetWeight);
        return userRepository.save(userToUpdate);
    }

}
