package com.gyl.api_gestionComercio.repository;

import com.gyl.api_gestionComercio.entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepository extends JpaRepository<Producto, Long> {
}
