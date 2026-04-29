package com.gyl.api_gestionComercio.repository;

import com.gyl.api_gestionComercio.entity.TipoProducto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TipoProductoRepository extends JpaRepository<TipoProducto, Long> {

    Optional<TipoProducto> findByNombre(String nombre);
}
