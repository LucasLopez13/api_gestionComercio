package com.gyl.api_gestionComercio.service;

import com.gyl.api_gestionComercio.dto.ClienteRequestDto;
import com.gyl.api_gestionComercio.dto.ClienteResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ClienteService {

    ClienteResponseDto crearCliente(ClienteRequestDto dto);

    Page<ClienteResponseDto> obtenerTodosLosClientes(Pageable pageable);

    ClienteResponseDto obtenerClientePorId(Long id);

    ClienteResponseDto actualizarCliente(Long id, ClienteRequestDto dto);

    void eliminarCliente(Long id);

}
