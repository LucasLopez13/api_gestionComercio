package com.gyl.api_gestionComercio.service;

import com.gyl.api_gestionComercio.dto.ClienteRequestDto;
import com.gyl.api_gestionComercio.dto.ClienteResponseDto;

import java.util.List;

public interface ClienteService {

    ClienteResponseDto crearCliente(ClienteRequestDto dto);

    List<ClienteResponseDto> obtenerTodosLosClientes();

    ClienteResponseDto obtenerClientePorId(Long id);

    ClienteResponseDto actualizarCliente(Long id, ClienteRequestDto dto);

    void eliminarCliente(Long id);

}
