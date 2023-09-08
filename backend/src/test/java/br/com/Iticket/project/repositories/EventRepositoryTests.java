package br.com.Iticket.project.repositories;

import br.com.Iticket.project.entities.Event;
import br.com.Iticket.project.tests.Factory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.EmptyResultDataAccessException;

import java.util.List;
import java.util.Optional;

@DataJpaTest
public class EventRepositoryTests {

    @Autowired
    private EventRepository repository;

    private long existingId;
    private long nonExistingId;

    private long countTotalEvents;

    @BeforeEach
    void setUp() {
        existingId = 1L;
        nonExistingId = 100L;
        countTotalEvents = 5;
    }

    @Test
    public void saveShouldPersistWithAutoIncrementWhenIdIsNull() {

        Event event = Factory.createEvent();
        event.setId(null);

        event = repository.save(event);

        Assertions.assertNotNull(event.getId());
        Assertions.assertEquals(countTotalEvents+1, event.getId());

    }

    @Test
    public void findByIdShouldReturnNotNullOptionWhenIdExists() {

        Optional<Event> obj = repository.findById(existingId);

        Assertions.assertNotNull(obj.isPresent());
    }

    @Test
    public void findByIdShouldReturnNullOptionWhenIdDoesNotExists() {

        Optional<Event> obj = repository.findById(nonExistingId);
        Assertions.assertFalse(obj.isPresent());

    }

    @Test
    public void findEventsByCityIdShouldReturnListOfEventsWhenCityIdExists() {

        List<Event> eventList = repository.findEventsByCityId(existingId);

        Assertions.assertNotNull(eventList);
        Assertions.assertFalse(eventList.isEmpty());

    }

    @Test
    public void findEventsByCityIdShouldReturnEmptyListWhenCityIdDoesNotExists() {

        List<Event> eventList = repository.findEventsByCityId(nonExistingId);

        Assertions.assertTrue(eventList.isEmpty());

    }

}
