package com.example.project.controller;

import com.example.project.DTO.EventDTO;
import com.example.project.services.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
