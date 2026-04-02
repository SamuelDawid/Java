package com.dailyTracker.habit_tracker.controller;

import com.dailyTracker.habit_tracker.dto.XpResponse;
import com.dailyTracker.habit_tracker.service.LevelingService;
import com.dailyTracker.habit_tracker.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.dailyTracker.habit_tracker.Constants.BEARER_PREFIX_LENGTH;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/progress")
public class LevelingController {
    private final UserService userService;
    private final LevelingService levelingService;
    @GetMapping("/xp")
    private ResponseEntity<XpResponse> getTotalXp(@RequestHeader("Authorization") String authHeader) throws Exception{
        Long userId = userService.getUserIdFromToken(authHeader.substring(BEARER_PREFIX_LENGTH)).orElseThrow(() -> new Exception("Invalid Token"));
        return ResponseEntity.ok(new XpResponse(levelingService.getTotalXp(userId),"The Condemned"));
    }
}
