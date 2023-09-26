package br.com.Iticket.project.controller;


import br.com.Iticket.project.DTO.AuthenticationDTO;
import br.com.Iticket.project.DTO.LoginResponseDTO;
import br.com.Iticket.project.DTO.UserInsertDTO;
import br.com.Iticket.project.service.AuthenticationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationService service;

    @PostMapping("/login")
    public ResponseEntity login (@RequestBody @Valid AuthenticationDTO dto) {
        LoginResponseDTO loginResponseDTO = service.login(dto);
        return ResponseEntity.ok(loginResponseDTO);
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid UserInsertDTO dto) {
        service.register(dto);
        return ResponseEntity.ok().build();
    }

}
