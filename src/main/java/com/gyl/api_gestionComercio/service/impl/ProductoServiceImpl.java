package com.gyl.api_gestionComercio.service.impl;

import com.gyl.api_gestionComercio.dto.request.ProductoRequestDto;
import com.gyl.api_gestionComercio.dto.request.updates.ProductoUpdateDto;
import com.gyl.api_gestionComercio.dto.response.ProductoResponseDto;
import com.gyl.api_gestionComercio.entity.Producto;
import com.gyl.api_gestionComercio.entity.TipoProducto;
import com.gyl.api_gestionComercio.exception.RecursoDuplicadoException;
import com.gyl.api_gestionComercio.exception.RecursoNoEncontradoExcepcion;
import com.gyl.api_gestionComercio.mapper.ProductoMapper;
import com.gyl.api_gestionComercio.repository.ProductoRepository;
import com.gyl.api_gestionComercio.repository.TipoProductoRepository;
import com.gyl.api_gestionComercio.service.ProductoService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductoServiceImpl implements ProductoService {

    private final ProductoRepository productoRepository;
    private final ProductoMapper productoMapper;
    private final TipoProductoRepository tipoProductoRepository;

    public ProductoServiceImpl(ProductoRepository productoRepository, ProductoMapper productoMapper, TipoProductoRepository tipoProductoRepository) {
        this.productoRepository = productoRepository;
        this.productoMapper = productoMapper;
        this.tipoProductoRepository = tipoProductoRepository;
    }

    @Override
    public ProductoResponseDto crearProducto(ProductoRequestDto dto) {
        productoRepository.findByNombre(dto.nombre()).ifPresent(p -> {
            throw new RecursoDuplicadoException("Ya existe un producto con el nombre: " + dto.nombre());
        });

        TipoProducto tipoProducto = obtenerTipoProducto(dto.idTipoProducto());

        Producto producto = productoMapper.toEntity(dto);
        producto.setTipoProducto(tipoProducto);

        return productoMapper.toResponseDto(productoRepository.save(producto));
    }

    @Override
    public Page<ProductoResponseDto> obtenerTodosLosProductos(Pageable pageable) {
        return productoRepository.findAll(pageable)
                .map(productoMapper::toResponseDto);
    }

    @Override
    public ProductoResponseDto obtenerProductoPorId(Long id) {
        Producto producto = buscarProductoPorId(id);
        return productoMapper.toResponseDto(producto);
    }

    @Override
    public ProductoResponseDto actualizarProducto(Long id, ProductoUpdateDto dto) {
        Producto producto = buscarProductoPorId(id);

        Optional.ofNullable(dto.nombre())
                .flatMap(nombre -> productoRepository.findByNombre(nombre))
                .filter(p -> !p.getIdProducto().equals(id))
                .ifPresent(p -> {
                    throw new RecursoDuplicadoException("El nombre del producto ya está en uso.");
                });

        productoMapper.updateEntityFromDTO(dto, producto);
        return productoMapper.toResponseDto(productoRepository.save(producto));
    }

    @Override
    public void eliminarProducto(Long id) {
        Producto producto = buscarProductoPorId(id);
        productoRepository.delete(producto);
    }

    private Producto buscarProductoPorId(Long id) {
        return productoRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoExcepcion("Producto no encontrado con el id: " + id));
    }

    private TipoProducto obtenerTipoProducto(Long id) {
        return tipoProductoRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoExcepcion("Tipo de Producto no encontrado con el id: " + id));
    }
}
