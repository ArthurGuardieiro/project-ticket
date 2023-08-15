package com.example.project.services;

import com.example.project.DTO.CityDTO;
import com.example.project.entities.City;
import com.example.project.repositories.CityRepository;
import com.example.project.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class CityService {

    @Autowired
    private CityRepository repository;

    @Transactional
    public CityDTO findById(Long id) {
        Optional<City> obj = repository.findById(id);
        City city = obj.orElseThrow( () -> new ResourceNotFoundException("id not found"));
        return new CityDTO(city);
    }


}
