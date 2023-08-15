package com.example.project.services;

import com.example.project.DTO.UserDTO;
import com.example.project.DTO.UserInsertDTO;
import com.example.project.entities.User;
import com.example.project.repositories.UserRepository;
import com.example.project.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    @Transactional(readOnly = true)
    public UserDTO findById(Long id){
        Optional<User> obj = repository.findById(id);
        User entity = obj.orElseThrow( () -> new ResourceNotFoundException("id not found"));
        return new UserDTO(entity);
    }

    public UserDTO insert(UserInsertDTO dto){
        User entity = new User();
        entity.setEmail(dto.getEmail());
        entity.setName(dto.getName());
        entity.setPassword(dto.getPassword());
        entity = repository.save(entity);
        return new UserDTO(entity);
    }




}
