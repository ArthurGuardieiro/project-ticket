package br.com.Iticket.project.services;


import br.com.Iticket.project.DTO.CityDTO;
import br.com.Iticket.project.entities.City;
import br.com.Iticket.project.repositories.CityRepository;
import br.com.Iticket.project.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class CityService {

    @Autowired
    private CityRepository repository;

    @Transactional(readOnly = true)
    public CityDTO findById(Long id) {
        Optional<City> obj = repository.findById(id);
        City city = obj.orElseThrow( () -> new ResourceNotFoundException("id not found"));
        return new CityDTO(city);
    }


    public CityDTO insert(CityDTO dto) {
        City entity = new City();
        entity.setName( dto.getName() );
        entity.setState( dto.getState() );
        entity = repository.save(entity);
        return new CityDTO(entity);
    }

}
