package com.gyl.api_gestionComercio.controller;

import com.gyl.api_gestionComercio.dto.request.ClienteRequestDto;
import com.gyl.api_gestionComercio.dto.request.updates.ClienteUpdateDto;
import com.gyl.api_gestionComercio.dto.response.ClienteResponseDto;
import com.gyl.api_gestionComercio.service.ClienteService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ClienteResponseDto crearCliente(@Valid @RequestBody ClienteRequestDto dto) {
        return clienteService.crearCliente(dto);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Page<ClienteResponseDto> listarClientes(@PageableDefault(size = 10) Pageable pageable) {
        return clienteService.obtenerTodosLosClientes(pageable);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ClienteResponseDto obtenerClientePorId(@PathVariable Long id) {
        return clienteService.obtenerClientePorId(id);
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ClienteResponseDto actualizarCliente(@PathVariable Long id, @Valid @RequestBody ClienteUpdateDto dto) {
        return clienteService.actualizarCliente(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminarCliente(@PathVariable Long id) {
        clienteService.eliminarCliente(id);
    }
}
