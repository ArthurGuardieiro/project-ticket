package com.example.project.services;

import com.example.project.DTO.UserDTO;
import com.example.project.DTO.UserInsertDTO;
import com.example.project.entities.City;
import com.example.project.entities.Role;
import com.example.project.entities.User;
import com.example.project.repositories.CityRepository;
import com.example.project.repositories.RoleRepository;
import com.example.project.repositories.UserRepository;
import com.example.project.services.exceptions.DatabaseException;
import com.example.project.services.exceptions.ResourceNotFoundException;
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

    @Transactional(readOnly = true)
    public UserDTO findById(Long id){
        Optional<User> obj = repository.findById(id);
        User entity = obj.orElseThrow( () -> new ResourceNotFoundException("id not found"));
        return new UserDTO(entity);
    }

    @Transactional
    public UserDTO insert(UserInsertDTO dto){
        User entity = new User();
        copyDtoToEntity(entity, dto);
        entity = repository.save(entity);
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
