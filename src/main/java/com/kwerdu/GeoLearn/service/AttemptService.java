package com.kwerdu.GeoLearn.service;

import com.kwerdu.GeoLearn.model.Attempt;
import com.kwerdu.GeoLearn.model.Region;
import com.kwerdu.GeoLearn.model.User;
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

    public List<Attempt> getAllByUser(User user){
        return attemptRepository.findByUser(user);
    }

    public List<Attempt> getAllByUserAndRegion(User user, Region region){
        return attemptRepository.findByUserAndRegion(user, region);
    }

    public List<Attempt> getLast10ByUserAndRegion(User user, Region region){
        return attemptRepository.findTop10ByUserAndRegionOrderByCreatedAtDesc(user, region);
    }

    public Attempt getById(int id){
        return attemptRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Попытка не найдена"));
    }

    public void create(Attempt attempt){
        attemptRepository.save(attempt);
    }

    public void delete(Attempt attempt){
        attemptRepository.delete(attempt);
    }
}
