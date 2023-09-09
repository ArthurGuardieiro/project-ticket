package br.com.Iticket.project.repositories;

import br.com.Iticket.project.entities.City;
import br.com.Iticket.project.tests.Factory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

@DataJpaTest
public class CityRepositoryTests {

    @Autowired
    private CityRepository repository;

    private long existingId;

    private long nonExistingId;

    private long countTotalCities;

    @BeforeEach
    void setUp() {
        existingId = 1L;
        nonExistingId = 1000L;
        countTotalCities = 4L;
    }

    @Test
    public void saveShouldPersistWithAutoIncrementWhenIdDoesNotExists() {

        City city = Factory.createCity();
        city.setId(null);

        city = repository.save(city);

        Assertions.assertNotNull(city.getId());
        Assertions.assertEquals(countTotalCities+1, city.getId());

    }

    @Test
    public void findByIdShouldReturnNotNullOptionWhenIdExists() {

        Optional<City> city = repository.findById(existingId);
        Assertions.assertNotNull(city.isPresent());

    }

    @Test
    public void findByIdShouldReturnNullOptionWhenIdDoesNotExists() {

        Optional<City> city = repository.findById(nonExistingId);
        Assertions.assertFalse(city.isPresent());

    }


}
