package com.example.project.services;

import com.example.project.DTO.TicketDTO;
import com.example.project.entities.Event;
import com.example.project.entities.Ticket;
import com.example.project.entities.User;
import com.example.project.repositories.EventRepository;
import com.example.project.repositories.TicketRepository;
import com.example.project.repositories.UserRepository;
import com.example.project.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TicketService {

    @Autowired
    private TicketRepository repository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EventRepository eventRepository;

    public TicketDTO findById (Long id) {
        Optional<Ticket> obj = repository.findById(id);
        Ticket entity = obj.orElseThrow( () -> new ResourceNotFoundException("id not found"));
        return new TicketDTO(entity);
    }

    public List<TicketDTO> findTicketsByUserId (Long userId) {
        Optional<User> obj = userRepository.findById(userId);
        obj.orElseThrow(() -> new ResourceNotFoundException("user id not found"));
        List<Ticket> tickets = repository.findTicketsByUserId(userId);
        List<TicketDTO> ticketDTOS = tickets.stream().map(x -> new TicketDTO(x)).collect(Collectors.toList());
        return ticketDTOS;
    }

    public TicketDTO insert (TicketDTO dto) {
        Ticket entity = new Ticket();
        copyDtoToEntity(entity, dto);
        entity = repository.save(entity);
        return new TicketDTO(entity);
    }

    private void copyDtoToEntity (Ticket entity, TicketDTO dto) {
        Optional<Event> objEvent = eventRepository.findById(dto.getEvent_id());
        Event event = objEvent.orElseThrow( () -> new ResourceNotFoundException("Event id not found!") );
        entity.setEvent(event);
        Optional<User> objUser = userRepository.findById(dto.getUser_id());
        User user = objUser.orElseThrow( () -> new ResourceNotFoundException("User id not found!"));
        entity.setUser(user);
        entity.setPrice(dto.getPrice());
    }

}
