package br.com.Iticket.project.service;

import br.com.Iticket.project.DTO.CityDTO;
import br.com.Iticket.project.model.City;
import br.com.Iticket.project.repository.CityRepository;
import br.com.Iticket.project.service.exceptions.ResourceNotFoundException;
import br.com.Iticket.project.tests.Factory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;

@ExtendWith(SpringExtension.class)
public class CityServiceTests {

    @InjectMocks
    private CityService service;

    @Mock
    private CityRepository repository;

    private long existingId;
    private long nonExistingId;
    private City city;

    @BeforeEach
    void setUp() {
        existingId = 1L;
        nonExistingId = 1000L;
        city = Factory.createCity();

        Mockito.when(repository.findById(existingId)).thenReturn(Optional.of(city));
        Mockito.when(repository.findById(nonExistingId)).thenReturn(Optional.empty());

        Mockito.when(repository.save( any() )).thenReturn(city);

    }

    @Test
    public void findByIdShouldReturnDTOWhenIdExists() {

        CityDTO dto = service.findById(existingId);

        Assertions.assertNotNull(dto);

    }

    @Test
    public void findByIdShouldThrowResourceNotFoundExceptionWhenIdDoesNotExists() {

        Assertions.assertThrows(ResourceNotFoundException.class, () -> {
            service.findById(nonExistingId);
        });

    }

    @Test
    public void insertShouldReturnDTO() {

        CityDTO dto = service.insert(new CityDTO(city));

        Assertions.assertNotNull(dto);

    }


}
