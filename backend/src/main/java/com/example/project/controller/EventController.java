package com.example.project.controller;

import com.example.project.DTO.EventDTO;
import com.example.project.services.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/events")
public class EventController {

    @Autowired
    private EventService service;

    @GetMapping(value = "/{id}")
    public ResponseEntity<EventDTO> findById (@PathVariable Long id) {
        EventDTO dto = service.findById(id);
        return ResponseEntity.ok(dto);
    }

    @GetMapping(value = "/{cityId}/events")
    public ResponseEntity<List<EventDTO>> findEventsByCityId (@PathVariable Long cityId) {
        List<EventDTO> dtos = service.findEventsByCityId(cityId);
        return ResponseEntity.ok(dtos);
    }

    @PostMapping
    public ResponseEntity<EventDTO> insert (@RequestBody EventDTO dto) {
        EventDTO newDto = service.insert(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(newDto.getId()).toUri();
        return ResponseEntity.created(uri).body(newDto);
    }

}
