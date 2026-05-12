package com.gyl.api_gestionComercio.service.impl;


import com.gyl.api_gestionComercio.dto.request.LoginRequestDTO;
import com.gyl.api_gestionComercio.dto.request.RegistroRequestDTO;
import com.gyl.api_gestionComercio.dto.response.RegistroResponseDTO;
import com.gyl.api_gestionComercio.dto.response.TokenResponseDTO;
import com.gyl.api_gestionComercio.entity.Usuario;
import com.gyl.api_gestionComercio.mapper.UsuarioMapper;
import com.gyl.api_gestionComercio.repository.UsuarioRepository;
import com.gyl.api_gestionComercio.security.TokenService;
import com.gyl.api_gestionComercio.service.AuthenticationService;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UsuarioRepository usuarioRepository;
    private final TokenService tokenService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final UsuarioMapper UsuarioMapper;

    @Override
    public RegistroResponseDTO registrar(RegistroRequestDTO dto) {
        String password = passwordEncoder.encode(dto.password());

        Usuario usuario = UsuarioMapper.toEntity(dto, password);

        usuarioRepository.save(usuario);

        return new RegistroResponseDTO(usuario.getUsername(), usuario.getEmail());
    }

    @Override
    public TokenResponseDTO login(LoginRequestDTO dto) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(dto.username(),dto.password())
        );

        UserDetails usuario = usuarioRepository.findByUsername(dto.username())
                .orElseThrow();

        String token = tokenService.getToken(usuario);
        return new TokenResponseDTO(token);
    }
}