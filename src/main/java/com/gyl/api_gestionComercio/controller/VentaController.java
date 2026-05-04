package com.gyl.api_gestionComercio.controller;

import com.gyl.api_gestionComercio.dto.VentaRequestDto;
import com.gyl.api_gestionComercio.dto.VentaResponseDto;
import com.gyl.api_gestionComercio.service.VentaService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ventas")
public class VentaController {

    private final VentaService ventaService;

    public VentaController(VentaService ventaService) {
        this.ventaService = ventaService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public VentaResponseDto registrarVenta(@Valid @RequestBody VentaRequestDto dto) {
        return ventaService.registrarVenta(dto);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Page<VentaResponseDto> obtenerTodasLasVentas(@PageableDefault(size = 10) Pageable pageable) {
        return ventaService.obtenerTodasLasVentas(pageable);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public VentaResponseDto obtenerVentaPorId(@PathVariable Long id) {
        return ventaService.obtenerVentaPorId(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminarVenta(@PathVariable Long id) {
        ventaService.eliminarVenta(id);
    }
}
