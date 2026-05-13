package com.gyl.api_gestionComercio.controller;

import com.gyl.api_gestionComercio.dto.request.TipoProductoRequestDto;
import com.gyl.api_gestionComercio.dto.request.updates.TipoProductoUpdateDto;
import com.gyl.api_gestionComercio.dto.response.TipoProductoResponseDto;
import com.gyl.api_gestionComercio.service.TipoProductoService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tipo_producto")
public class TipoProductoController {

    private final TipoProductoService tipoProductoService;

    public TipoProductoController(TipoProductoService tipoProductoService) {
        this.tipoProductoService = tipoProductoService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TipoProductoResponseDto crearTipoProducto(@Valid @RequestBody TipoProductoRequestDto dto) {
        return tipoProductoService.crearTipoProducto(dto);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Page<TipoProductoResponseDto> obtenerTodosLosTiposProductos(@PageableDefault(size = 10) Pageable pageable) {
        return tipoProductoService.obtenerTodosLosTiposProductos(pageable);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public TipoProductoResponseDto obtenerTipoProductoPorId(@PathVariable Long id) {
        return tipoProductoService.obtenerTipoProductoPorId(id);
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public TipoProductoResponseDto actualizarTipoProducto(@PathVariable Long id, @Valid @RequestBody TipoProductoUpdateDto dto) {
        return tipoProductoService.actualizarTipoProducto(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminarTipoProducto(@PathVariable Long id) {
        tipoProductoService.eliminarTipoProducto(id);
    }

}
