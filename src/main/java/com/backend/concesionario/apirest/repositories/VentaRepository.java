package com.backend.concesionario.apirest.repositories;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.backend.concesionario.apirest.entities.Venta;

@Repository
public interface VentaRepository extends CrudRepository<Venta, Long> {
}

