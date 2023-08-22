package br.com.Iticket.project.services;


import br.com.Iticket.project.DTO.UserDTO;
import br.com.Iticket.project.DTO.UserInsertDTO;
import br.com.Iticket.project.entities.City;
import br.com.Iticket.project.entities.Role;
import br.com.Iticket.project.entities.User;
import br.com.Iticket.project.repositories.CityRepository;
import br.com.Iticket.project.repositories.RoleRepository;
import br.com.Iticket.project.repositories.UserRepository;
import br.com.Iticket.project.services.exceptions.DatabaseException;
import br.com.Iticket.project.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private CityRepository cityRepository;
    @Autowired
    private AuthService authService;

    @Transactional(readOnly = true)
    public UserDTO findById(Long id){
        authService.validateSelfOrAdmin(id);
        Optional<User> obj = repository.findById(id);
        User entity = obj.orElseThrow( () -> new ResourceNotFoundException("id not found"));
        return new UserDTO(entity);
    }

    @Transactional
    public UserDTO update(Long id, UserInsertDTO dto) {
        try{
            User entity = repository.getOne(id);
            copyDtoToEntity(entity, dto);
            entity = repository.save(entity);
            return new UserDTO(entity);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Id not found");
        }
    }

    public void delete(Long id) {
        try {
            Optional<User> obj = repository.findById(id);
            User entity = obj.orElseThrow( () -> new ResourceNotFoundException("id not found " + id) );
            repository.delete(entity);
        }
        catch(DataIntegrityViolationException e) {
            throw new DatabaseException("Integrity violation");
        }
    }

    private void copyDtoToEntity(User entity, UserInsertDTO dto) {
        entity.setEmail(dto.getEmail());
        entity.setName(dto.getName());
        entity.setPassword(dto.getPassword());
        Optional<Role> roleObj = roleRepository.findById(dto.getRole_id());
        Role role = roleObj.orElseThrow( () -> new ResourceNotFoundException("role id not found") );
        entity.setRole(role);
        Optional<City> cityObj = cityRepository.findById(dto.getCity_id());
        City city = cityObj.orElseThrow( () -> new ResourceNotFoundException("city id not found") );
        entity.setCity(city);
    }


}
