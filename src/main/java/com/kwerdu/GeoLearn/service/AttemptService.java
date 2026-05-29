package com.kwerdu.GeoLearn.service;

import com.kwerdu.GeoLearn.model.Attempt;
import com.kwerdu.GeoLearn.repository.AttemptRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AttemptService {

    private final AttemptRepository attemptRepository;

    public AttemptService(AttemptRepository attemptRepository){
        this.attemptRepository = attemptRepository;
    }

    public List<Attempt> getAll(){
        return attemptRepository.findAll();
    }

    public Attempt getById(int id){
        return attemptRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Попытка не найдена"));
    }

    public void create(Attempt attempt){
        attemptRepository.save(attempt);
    }
}
