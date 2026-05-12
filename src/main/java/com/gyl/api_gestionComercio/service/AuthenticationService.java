package com.gyl.api_gestionComercio.service;


import com.gyl.api_gestionComercio.dto.request.LoginRequestDTO;
import com.gyl.api_gestionComercio.dto.request.RegistroRequestDTO;
import com.gyl.api_gestionComercio.dto.response.RegistroResponseDTO;
import com.gyl.api_gestionComercio.dto.response.TokenResponseDTO;

public interface AuthenticationService {
    RegistroResponseDTO registrar(RegistroRequestDTO dto);

    TokenResponseDTO login(LoginRequestDTO dto);
}