package com.gyl.api_gestionComercio.repository;

import com.gyl.api_gestionComercio.entity.TipoProducto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TipoProductoRepository extends JpaRepository<TipoProducto, Long> {

    Optional<TipoProducto> findByNombre(String nombre);
}
