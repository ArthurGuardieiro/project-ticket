package com.example.project.services;

import com.example.project.DTO.EventDTO;
import com.example.project.entities.City;
import com.example.project.entities.Event;
import com.example.project.repositories.CityRepository;
import com.example.project.repositories.EventRepository;
import com.example.project.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EventService {

    @Autowired
    private EventRepository repository;

    @Autowired
    private CityRepository cityRepository;


    @Transactional(readOnly = true)
    public EventDTO findById(Long id) {
        Optional<Event> obj = repository.findById(id);
        Event entity = obj.orElseThrow(() -> new ResourceNotFoundException("id not found"));
        return new EventDTO(entity);
    }

    @Transactional(readOnly = true)
    public List<EventDTO> findEventsByCityId(Long cityId) {
        Optional<City> obj = cityRepository.findById(cityId);
        obj.orElseThrow( () -> new ResourceNotFoundException("city id not found") );
        List<Event> events = repository.findEventsByCityId(cityId);
        List<EventDTO> eventDTOS = events.stream().map( x -> new EventDTO(x) ).collect(Collectors.toList());
        return eventDTOS;
    }

    @Transactional
    public EventDTO insert (EventDTO dto) {
        Event entity = new Event();
        copyDtoToEntity(entity, dto);
        entity = repository.save(entity);
        return new EventDTO(entity);
    }

    private void copyDtoToEntity(Event entity, EventDTO dto) {
        entity.setName( dto.getName() );
        entity.setAddres( dto.getAddres() );
        Optional<City> obj = cityRepository.findById( dto.getCityId() );
        City city =  obj.orElseThrow( () -> new ResourceNotFoundException("city id not found") );
        entity.setCity(city);
    }

}
