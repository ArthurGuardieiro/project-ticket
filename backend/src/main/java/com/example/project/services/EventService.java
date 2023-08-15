package com.example.project.services;

import com.example.project.DTO.EventDTO;
import com.example.project.entities.Event;
import com.example.project.repositories.EventRepository;
import com.example.project.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class EventService {

    @Autowired
    private EventRepository repository;


    @Transactional(readOnly = true)
    public EventDTO findById(Long id) {
        Optional<Event> obj = repository.findById(id);
        Event entity = obj.orElseThrow(() -> new ResourceNotFoundException("id not found"));
        return new EventDTO(entity);
    }


}
