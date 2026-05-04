package com.gyl.api_gestionComercio.controller;

import com.gyl.api_gestionComercio.dto.ProductoRequestDto;
import com.gyl.api_gestionComercio.dto.ProductoResponseDto;
import com.gyl.api_gestionComercio.service.ProductoService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    private final ProductoService productoService;

    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductoResponseDto crearProducto(@Valid @RequestBody ProductoRequestDto dto) {
        return productoService.crearProducto(dto);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Page<ProductoResponseDto> obtenerTodosLosProductos(@PageableDefault(size = 10) Pageable pageable) {
        return productoService.obtenerTodosLosProductos(pageable);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ProductoResponseDto obtenerProductoPorId(@PathVariable Long id) {
        return productoService.obtenerProductoPorId(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ProductoResponseDto actualizarProducto(@PathVariable Long id, @Valid @RequestBody ProductoRequestDto dto) {
        return productoService.actualizarProducto(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminarProducto(@PathVariable Long id) {
        productoService.eliminarProducto(id);
    }
}
