package com.gyl.api_gestionComercio.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "productos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProducto;

    private String nombre;

    private BigDecimal precio;

    private Integer stock;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tipo_producto")
    private TipoProducto tipoProducto;
}
