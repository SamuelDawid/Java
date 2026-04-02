package com.dailyTracker.habit_tracker.service;

import com.dailyTracker.habit_tracker.model.HabitEntry;
import com.dailyTracker.habit_tracker.repository.HabitEntryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LevelingService {
    private final HabitEntryRepository habitRepository;

    public Integer getTotalXp(Long userId) throws Exception {
        List<HabitEntry> allHabitEntryForUser = habitRepository.findByDailyLog_UserId (userId);
        if(!allHabitEntryForUser.isEmpty()){
            return allHabitEntryForUser.stream().mapToInt(HabitEntry::getXpEarned).sum();
        }
        return 0;
    }

public String getTitle(Integer xp){
 // to do
    return "Titles API NEED WORK";
}
}
