package br.com.Iticket.project.controller;


import br.com.Iticket.project.DTO.AuthenticationDTO;
import br.com.Iticket.project.DTO.LoginResponseDTO;
import br.com.Iticket.project.DTO.UserInsertDTO;
import br.com.Iticket.project.config.TokenService;
import br.com.Iticket.project.entities.City;
import br.com.Iticket.project.entities.Role;
import br.com.Iticket.project.entities.User;
import br.com.Iticket.project.repositories.CityRepository;
import br.com.Iticket.project.repositories.RoleRepository;
import br.com.Iticket.project.repositories.UserRepository;
import br.com.Iticket.project.services.exceptions.ResourceNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository repository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity login (@RequestBody @Valid AuthenticationDTO dto) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(dto.getLogin(), dto.getPassword());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        var token = tokenService.generateToken((User) auth.getPrincipal());

        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid UserInsertDTO dto) {
        if(this.repository.findByEmail(dto.getEmail()) != null) return ResponseEntity.badRequest().build();

        String encryptedPassword = new BCryptPasswordEncoder().encode(dto.getPassword());
        User user = new User(dto.getEmail(), encryptedPassword, dto.getName());

        Optional<Role> roleObj = roleRepository.findById(dto.getRole_id());
        Role role = roleObj.orElseThrow( () -> new ResourceNotFoundException("Role id not found!"));
        user.setRole(role);

        Optional<City> cityObj = cityRepository.findById(dto.getCity_id());
        City city = cityObj.orElseThrow( () -> new ResourceNotFoundException("City id not found!"));
        user.setCity(city);

        user = repository.save(user);

        return ResponseEntity.ok().build();
    }

}
