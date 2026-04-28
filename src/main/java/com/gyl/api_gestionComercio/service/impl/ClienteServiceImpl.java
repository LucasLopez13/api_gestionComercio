package com.gyl.api_gestionComercio.service.impl;

import com.gyl.api_gestionComercio.dto.ClienteRequestDto;
import com.gyl.api_gestionComercio.dto.ClienteResponseDto;
import com.gyl.api_gestionComercio.entity.Cliente;
import com.gyl.api_gestionComercio.exception.RecursoNoEncontradoExcepcion;
import com.gyl.api_gestionComercio.mapper.ClienteMapper;
import com.gyl.api_gestionComercio.repository.ClienteRepository;
import com.gyl.api_gestionComercio.service.ClienteService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteServiceImpl implements ClienteService {

    private final ClienteRepository clienteRepository;
    private final ClienteMapper clienteMapper;

    public ClienteServiceImpl(ClienteRepository clienteRepository, ClienteMapper clienteMapper) {
        this.clienteRepository = clienteRepository;
        this.clienteMapper = clienteMapper;
    }

    @Override
    public ClienteResponseDto crearCliente(ClienteRequestDto dto) {
        clienteRepository.findByEmail(dto.email()).ifPresent(cliente -> {
            throw new IllegalArgumentException("Ya existe un cliente con el email: " + dto.email());
        });
        Cliente cliente = clienteMapper.toEntity(dto);
        Cliente clienteGuardado = clienteRepository.save(cliente);
        return clienteMapper.toResponseDTO(clienteGuardado);
    }


    @Override
    public List<ClienteResponseDto> obtenerTodosLosClientes() {
        return clienteRepository.findAll().stream()
                .map(clienteMapper::toResponseDTO)
                .toList();
    }

    @Override
    public ClienteResponseDto obtenerClientePorId(Long id) {
        Cliente cliente = buscarClientePorId(id);
        return clienteMapper.toResponseDTO(cliente);
    }

    @Override
    public ClienteResponseDto actualizarCliente(Long id, ClienteRequestDto dto) {
        Cliente cliente = buscarClientePorId(id);

        Optional<Cliente> clientePorEmail = clienteRepository.findByEmail(dto.email());

        if (clientePorEmail.isPresent() && clientePorEmail.get().getIdCliente().equals(id)) {
            throw new IllegalArgumentException("El email ya está en uso por otro cliente.");
        }

        clienteMapper.updateEntityFromDTO(dto, cliente);
        Cliente clienteActualizado = clienteRepository.save(cliente);
        return clienteMapper.toResponseDTO(clienteActualizado);
    }

    @Override
    public void eliminarCliente(Long id) {
        Cliente cliente = buscarClientePorId(id);
        clienteRepository.delete(cliente);
    }

    private Cliente buscarClientePorId(Long id) {
        return clienteRepository.findById(id).
                orElseThrow(() -> new RecursoNoEncontradoExcepcion("Cliente no encontrado con el id: " + id + "."));
    }

}
