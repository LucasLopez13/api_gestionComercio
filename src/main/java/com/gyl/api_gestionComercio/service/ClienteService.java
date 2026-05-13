package com.gyl.api_gestionComercio.service;

import com.gyl.api_gestionComercio.dto.request.ClienteRequestDto;
import com.gyl.api_gestionComercio.dto.request.updates.ClienteUpdateDto;
import com.gyl.api_gestionComercio.dto.response.ClienteResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ClienteService {

    ClienteResponseDto crearCliente(ClienteRequestDto dto);

    Page<ClienteResponseDto> obtenerTodosLosClientes(Pageable pageable);

    ClienteResponseDto obtenerClientePorId(Long id);

    ClienteResponseDto actualizarCliente(Long id, ClienteUpdateDto dto);

    void eliminarCliente(Long id);

}
