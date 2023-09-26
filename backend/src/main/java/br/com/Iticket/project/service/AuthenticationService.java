package br.com.Iticket.project.service;

import br.com.Iticket.project.DTO.AuthenticationDTO;
import br.com.Iticket.project.DTO.LoginResponseDTO;
import br.com.Iticket.project.DTO.UserInsertDTO;
import br.com.Iticket.project.config.TokenService;
import br.com.Iticket.project.model.City;
import br.com.Iticket.project.model.Role;
import br.com.Iticket.project.model.User;
import br.com.Iticket.project.repository.CityRepository;
import br.com.Iticket.project.repository.RoleRepository;
import br.com.Iticket.project.repository.UserRepository;
import br.com.Iticket.project.service.exceptions.DatabaseException;
import br.com.Iticket.project.service.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthenticationService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserRepository repository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private CityRepository cityRepository;

    public LoginResponseDTO login (AuthenticationDTO dto) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(dto.getLogin(), dto.getPassword());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        var token = tokenService.generateToken((User) auth.getPrincipal());
        return new LoginResponseDTO(token);
    }

    public void register(UserInsertDTO dto) {
        if(this.repository.findByEmail(dto.getEmail()) != null) throw new DatabaseException("Email já cadastrado no sistema!");

        String encryptedPassword = new BCryptPasswordEncoder().encode(dto.getPassword());
        User user = new User(dto.getEmail(), encryptedPassword, dto.getName());

        Optional<Role> roleObj = roleRepository.findById(dto.getRole_id());
        Role role = roleObj.orElseThrow( () -> new ResourceNotFoundException("Role id not found!"));
        user.setRole(role);

        Optional<City> cityObj = cityRepository.findById(dto.getCity_id());
        City city = cityObj.orElseThrow( () -> new ResourceNotFoundException("City id not found!"));
        user.setCity(city);

        user = repository.save(user);
    }

}
