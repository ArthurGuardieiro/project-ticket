package br.com.Iticket.project.services;

import static org.mockito.ArgumentMatchers.any;
import br.com.Iticket.project.DTO.EventDTO;
import br.com.Iticket.project.entities.City;
import br.com.Iticket.project.entities.Event;
import br.com.Iticket.project.repositories.CityRepository;
import br.com.Iticket.project.repositories.EventRepository;
import br.com.Iticket.project.services.exceptions.ResourceNotFoundException;
import br.com.Iticket.project.tests.Factory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@ExtendWith(SpringExtension.class)
public class EventServiceTests {

    @InjectMocks
    private EventService service;

    @Mock
    private EventRepository repository;

    @Mock
    private CityRepository cityRepository;

    private long existingId;
    private long nonExistingId;
    private Event event;
    private City city;
    private PageImpl<Event> page;

    @BeforeEach
    private void setUp() throws Exception {

        existingId = 1l;
        nonExistingId = 1000l;
        event = Factory.createEvent();
        city = Factory.createCity();
        page = new PageImpl<>(List.of(event));

        Mockito.when(repository.findById(existingId)).thenReturn(Optional.of(event));
        Mockito.when(repository.findById(nonExistingId)).thenReturn(Optional.empty());

        Mockito.when(repository.findEventsByCityId(existingId)).thenReturn(List.of(event));

        Mockito.when(cityRepository.findById(existingId)).thenReturn(Optional.of(city));
        Mockito.when(cityRepository.findById(nonExistingId)).thenReturn(Optional.empty());

        Mockito.when(repository.findAll( (Pageable)  any())).thenReturn(page);

        Mockito.when(repository.save(any())).thenReturn(event);


    }


    @Test
    public void findByIdShouldReturnDTOWhenIdExists() {

        EventDTO dto = service.findById(existingId);

        Assertions.assertNotNull(dto);
        Mockito.verify(repository).findById(existingId);

    }

    @Test
    public void findByIdShouldThrowResourceNotFoundExceptionWhenIdDoesNotExists() {

        Assertions.assertThrows(ResourceNotFoundException.class, () -> {
            service.findById(nonExistingId);
        });

    }

    @Test
    public void findEventsByCityIdShouldReturnListDTOWhenCityIdExists() {

        List<EventDTO> eventDTOS = service.findEventsByCityId(existingId);
        Assertions.assertNotNull(eventDTOS);

    }

    @Test
    public void findEventsByCityIdShouldThrowResourceNotFoundExceptionWhenCityIdDoesNotExists() {

        Assertions.assertThrows(ResourceNotFoundException.class, () -> {
           service.findEventsByCityId(nonExistingId);
        });

    }

    @Test
    public void findAllPagedShouldReturnPageDTO() {

        Pageable pageable = PageRequest.of(0, 10);

        Page<EventDTO> result = service.findAllPaged(pageable);

        Assertions.assertNotNull(result);

    }

    @Test
    public void InsertShouldPersist() {



    }



}
