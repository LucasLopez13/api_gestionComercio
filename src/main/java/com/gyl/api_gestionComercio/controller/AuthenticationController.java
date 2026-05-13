package com.gyl.api_gestionComercio.controller;


import com.gyl.api_gestionComercio.dto.request.LoginRequestDTO;
import com.gyl.api_gestionComercio.dto.request.RegistroRequestDTO;
import com.gyl.api_gestionComercio.dto.response.RegistroResponseDTO;
import com.gyl.api_gestionComercio.dto.response.TokenResponseDTO;
import com.gyl.api_gestionComercio.service.AuthenticationService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public RegistroResponseDTO registrar(@Valid @RequestBody RegistroRequestDTO dto) {
        return authenticationService.registrar(dto);
    }

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public TokenResponseDTO autenticar(@Valid @RequestBody LoginRequestDTO dto) {
        return authenticationService.login(dto);
    }
}