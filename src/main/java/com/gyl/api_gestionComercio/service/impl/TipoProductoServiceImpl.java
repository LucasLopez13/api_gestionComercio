package com.gyl.api_gestionComercio.service.impl;

import com.gyl.api_gestionComercio.dto.request.TipoProductoRequestDto;
import com.gyl.api_gestionComercio.dto.request.updates.TipoProductoUpdateDto;
import com.gyl.api_gestionComercio.dto.response.TipoProductoResponseDto;
import com.gyl.api_gestionComercio.entity.TipoProducto;
import com.gyl.api_gestionComercio.exception.RecursoDuplicadoException;
import com.gyl.api_gestionComercio.exception.RecursoNoEncontradoExcepcion;
import com.gyl.api_gestionComercio.mapper.TipoProductoMapper;
import com.gyl.api_gestionComercio.repository.TipoProductoRepository;
import com.gyl.api_gestionComercio.service.TipoProductoService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TipoProductoServiceImpl implements TipoProductoService {

    private final TipoProductoRepository tipoProductoRepository;
    private final TipoProductoMapper tipoProductoMapper;

    public TipoProductoServiceImpl(TipoProductoRepository tipoProductoRepository, TipoProductoMapper tipoProductoMapper) {
        this.tipoProductoRepository = tipoProductoRepository;
        this.tipoProductoMapper = tipoProductoMapper;
    }


    @Override
    public TipoProductoResponseDto crearTipoProducto(TipoProductoRequestDto dto) {
        tipoProductoRepository.findByNombre(dto.nombre()).ifPresent(tipoProducto -> {
            throw new RecursoDuplicadoException("Ya existe un tipo de producto con el nombre: " + dto.nombre());
        });
        TipoProducto tipoProducto = tipoProductoMapper.toEntity(dto);
        TipoProducto tipoProductoGuardado = tipoProductoRepository.save(tipoProducto);
        return tipoProductoMapper.toResponseDto(tipoProductoGuardado);
    }

    @Override
    public Page<TipoProductoResponseDto> obtenerTodosLosTiposProductos(Pageable pageable) {
        return tipoProductoRepository.findAll(pageable)
                .map(tipoProductoMapper::toResponseDto);
    }

    @Override
    public TipoProductoResponseDto obtenerTipoProductoPorId(Long id) {
        TipoProducto tipoProducto = buscarTipoProductoPorId(id);
        return tipoProductoMapper.toResponseDto(tipoProducto);
    }

    @Override
    public TipoProductoResponseDto actualizarTipoProducto(Long id, TipoProductoUpdateDto dto) {
        TipoProducto tipoProducto = buscarTipoProductoPorId(id);

        Optional.ofNullable(dto.nombre())
                .flatMap(n -> tipoProductoRepository.findByNombre(n))
                .filter(t -> !t.getIdTipoProducto().equals(id))
                .ifPresent(t -> { throw new RecursoDuplicadoException("Nombre de categoría duplicado."); });

        tipoProductoMapper.updateEntityFromDTO(dto, tipoProducto);
        TipoProducto tipoProductoActualizado = tipoProductoRepository.save(tipoProducto);
        return tipoProductoMapper.toResponseDto(tipoProductoActualizado);
    }

    @Override
    public void eliminarTipoProducto(Long id) {
        TipoProducto tipoProducto = buscarTipoProductoPorId(id);
        tipoProductoRepository.delete(tipoProducto);
    }

    private TipoProducto buscarTipoProductoPorId(Long id) {
        return tipoProductoRepository.findById(id).
                orElseThrow(() -> new RecursoNoEncontradoExcepcion("Tipo de Producto no encontrado con el id: " + id + "."));
    }
}
