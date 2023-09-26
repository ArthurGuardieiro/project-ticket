package br.com.Iticket.project.service;


import br.com.Iticket.project.DTO.TicketDTO;
import br.com.Iticket.project.model.Event;
import br.com.Iticket.project.model.Ticket;
import br.com.Iticket.project.model.User;
import br.com.Iticket.project.repository.EventRepository;
import br.com.Iticket.project.repository.TicketRepository;
import br.com.Iticket.project.repository.UserRepository;
import br.com.Iticket.project.service.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class TicketService {

    @Autowired
    private TicketRepository repository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private AuthService authService;

    @Transactional(readOnly = true)
    public TicketDTO findById (Long id) {
        Optional<Ticket> obj = repository.findById(id);
        Ticket entity = obj.orElseThrow( () -> new ResourceNotFoundException("id not found"));
        authService.validateSelfOrAdmin(entity.getUser().getId());
        return new TicketDTO(entity);
    }

    @Transactional(readOnly = true)
    public Page<TicketDTO> findTicketsByCurrentUser (Pageable pageable) {
        User user = authService.authenticated();
        Page<Ticket> page = repository.findByUser(user, pageable);
        return page.map( x -> new TicketDTO(x));
    }

    @Transactional
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
