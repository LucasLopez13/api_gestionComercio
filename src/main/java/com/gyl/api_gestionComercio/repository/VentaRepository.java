package com.gyl.api_gestionComercio.repository;

import com.gyl.api_gestionComercio.entity.Venta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VentaRepository extends JpaRepository<Venta, Long> {
}
