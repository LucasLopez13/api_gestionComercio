package com.gyl.api_gestionComercio.service.impl;

import com.gyl.api_gestionComercio.dto.DetallesVentaRequestDto;
import com.gyl.api_gestionComercio.dto.VentaRequestDto;
import com.gyl.api_gestionComercio.dto.VentaResponseDto;
import com.gyl.api_gestionComercio.entity.Cliente;
import com.gyl.api_gestionComercio.entity.DetalleVenta;
import com.gyl.api_gestionComercio.entity.Producto;
import com.gyl.api_gestionComercio.entity.Venta;
import com.gyl.api_gestionComercio.exception.RecursoNoEncontradoExcepcion;
import com.gyl.api_gestionComercio.exception.StockInsuficienteException;
import com.gyl.api_gestionComercio.mapper.VentaMapper;
import com.gyl.api_gestionComercio.repository.ClienteRepository;
import com.gyl.api_gestionComercio.repository.ProductoRepository;
import com.gyl.api_gestionComercio.repository.VentaRepository;
import com.gyl.api_gestionComercio.service.VentaService;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class VentaServiceImpl implements VentaService {

    private final VentaRepository ventaRepository;
    private final ClienteRepository clienteRepository;
    private final ProductoRepository productoRepository;
    private final VentaMapper ventaMapper;

    public VentaServiceImpl(VentaRepository ventaRepository, ClienteRepository clienteRepository, ProductoRepository productoRepository, VentaMapper ventaMapper) {
        this.ventaRepository = ventaRepository;
        this.clienteRepository = clienteRepository;
        this.productoRepository = productoRepository;
        this.ventaMapper = ventaMapper;
    }


    @Override
    @Transactional
    public VentaResponseDto registrarVenta(VentaRequestDto dto) {
        Cliente cliente = obtenerCliente(dto.idCliente());
        Venta venta = inicializarVenta(cliente);

        List<DetalleVenta> detalles = dto.detallesVenta().stream()
                .map(detalleDto -> construirDetalle(detalleDto, venta))
                .toList();

        BigDecimal totalVenta = detalles.stream()
                .map(DetalleVenta::getSubtotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        venta.setDetalles(detalles);
        venta.setTotal(totalVenta);

        return ventaMapper.toResponseDto(ventaRepository.save(venta));
    }

    @Override
    public Page<VentaResponseDto> obtenerTodasLasVentas(Pageable pageable) {
        return ventaRepository.findAll(pageable)
                .map(ventaMapper::toResponseDto);
    }

    @Override
    public VentaResponseDto obtenerVentaPorId(Long id) {
        Venta venta = obtenerVenta(id);
        return ventaMapper.toResponseDto(venta);
    }

    @Override
    @Transactional
    public void eliminarVenta(Long id) {
        Venta venta = obtenerVenta(id);

        venta.getDetalles().forEach(detalle -> {
            Producto producto = detalle.getProducto();
            producto.setStock(producto.getStock() + detalle.getCantidad());
        });

        ventaRepository.delete(venta);

    }

    private Cliente obtenerCliente(Long idCliente) {
        return clienteRepository.findById(idCliente)
                .orElseThrow(() -> new RecursoNoEncontradoExcepcion("Cliente no encontrado con el id: " + idCliente + "."));
    }

    private Venta obtenerVenta(Long idVenta) {
        return ventaRepository.findById(idVenta)
                .orElseThrow(() -> new RecursoNoEncontradoExcepcion("Venta no encontrada con el id: " + idVenta + "."));
    }

    private Venta inicializarVenta(Cliente cliente) {
        Venta venta = new Venta();
        venta.setCliente(cliente);
        venta.setFechaVenta(LocalDateTime.now());
        return venta;
    }

    private DetalleVenta construirDetalle(DetallesVentaRequestDto detalleReq, Venta venta) {
        Producto producto = productoRepository.findById(detalleReq.idProducto())
                .orElseThrow(() -> new RecursoNoEncontradoExcepcion("Producto no encontrado con el id: " + detalleReq.idProducto() + "."));

        validarYDescontarStock(producto, detalleReq.cantidad());

        BigDecimal subtotal = producto.getPrecio().multiply(new BigDecimal(detalleReq.cantidad()));

        DetalleVenta detalleVenta = new DetalleVenta();
        detalleVenta.setVenta(venta);
        detalleVenta.setProducto(producto);
        detalleVenta.setCantidad(detalleReq.cantidad());
        detalleVenta.setPrecioUnitario(producto.getPrecio());
        detalleVenta.setSubtotal(subtotal);

        return detalleVenta;
    }

    private void validarYDescontarStock(Producto producto, Integer cantidadRequerida) {
        if (producto.getStock() < cantidadRequerida) {
            throw new StockInsuficienteException("No hay suficiente stock para el producto con el id: " + producto.getIdProducto() + ".");
        }
        producto.setStock(producto.getStock() - cantidadRequerida);
    }
}
