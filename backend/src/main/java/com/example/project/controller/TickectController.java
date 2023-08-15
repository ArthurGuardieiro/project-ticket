package com.example.project.controller;

import com.example.project.DTO.TicketDTO;
import com.example.project.services.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
