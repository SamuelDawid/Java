package com.dailyTracker.habit_tracker.service;

import com.dailyTracker.habit_tracker.model.Habit;
import com.dailyTracker.habit_tracker.repository.HabitRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HabitService {
    private final HabitRepository habitRepository;

    public List<Habit> findAll(){
        return habitRepository.findAll();
    }

    public Habit save(Habit habit){
        return habitRepository.save(habit);
    }
    public void deleteById(Long id){
        habitRepository.deleteById(id);
    }
}
