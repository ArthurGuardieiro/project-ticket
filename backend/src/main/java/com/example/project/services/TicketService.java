package com.example.project.services;

import com.example.project.DTO.TicketDTO;
import com.example.project.entities.Ticket;
import com.example.project.repositories.TicketRepository;
import com.example.project.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TicketService {

    @Autowired
    private TicketRepository repository;

    public TicketDTO findById (Long id) {
        Optional<Ticket> obj = repository.findById(id);
        Ticket entity = obj.orElseThrow( () -> new ResourceNotFoundException("id not found"));
        return new TicketDTO(entity);
    }


}
