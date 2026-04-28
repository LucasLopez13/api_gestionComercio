package com.gyl.api_gestionComercio.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

import java.util.List;

@Entity
@Table(name = "tipos_producto")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SQLDelete(sql = "UPDATE tipos_producto SET activo = false WHERE id_tipo_producto=?")
@SQLRestriction("activo = true")
public class TipoProducto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTipoProducto;

    @NotBlank
    private String nombre;

    private String descripcion;

    @Column(name = "activo", nullable = false)
    private boolean activo = true;

    @OneToMany(mappedBy = "tipoProducto")
    private List<Producto> productos;
}
