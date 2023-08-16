package com.example.project.controller;

import com.example.project.DTO.TicketDTO;
import com.example.project.services.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/tickets")
public class TickectController {

    @Autowired
    private TicketService service;

    @GetMapping(value = "/{id}")
    public ResponseEntity<TicketDTO> findById(@PathVariable Long id) {
        TicketDTO dto = service.findById(id);
        return ResponseEntity.ok(dto);
    }

    @GetMapping(value = "/{userId}/tickets")
    public ResponseEntity<List<TicketDTO>> findTicketsByUserId(@PathVariable Long userId) {
        List<TicketDTO> dto = service.findTicketsByUserId(userId);
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<TicketDTO> insert (@RequestBody TicketDTO dto) {
        TicketDTO newDto = service.insert(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("{id}")
                .buildAndExpand(newDto.getId()).toUri();
        return ResponseEntity.created(uri).body(newDto);
    }

}
