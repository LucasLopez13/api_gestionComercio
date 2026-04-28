package com.gyl.api_gestionComercio.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "tipos_producto")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TipoProducto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTipoProducto;

    @NotBlank
    private String nombre;

    private String descripcion;

    @OneToMany(mappedBy = "tipoProducto")
    private List<Producto> productos;
}
