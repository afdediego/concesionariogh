package com.backend.concesionario.apirest.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.backend.concesionario.apirest.dtos.VentaDTO;
import com.backend.concesionario.apirest.entities.Venta;
import com.backend.concesionario.apirest.repositories.VentaRepository;

import java.util.Optional;

@Service
public class VentaService {

    @Autowired
    private VentaRepository ventaRepository;

    @Transactional(readOnly = true)
    public Iterable<Venta> findAll() {
        return ventaRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<Venta> findById(Long id) {
        return ventaRepository.findById(id);
    }

    @Transactional
    public Venta save(Venta venta) {
        return ventaRepository.save(venta);
    }

    @Transactional
    public void deleteById(Long id) {
        ventaRepository.deleteById(id);
    }
    
    public VentaDTO obtenerVentaPorId(Long id) {
        Venta venta = ventaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Venta no encontrada con el ID: " + id));
        // Construir el DTO a partir de los datos de la venta, cliente y vehículo
        return new VentaDTO(
                venta.getId(),
                venta.getFechaVenta(),
                venta.getCliente().getNombre(),
                venta.getCliente().getApellidos(),
                venta.getCliente().getDireccion(),
                venta.getCliente().getTelefono(),
                venta.getCliente().getEmail(),
                venta.getVehiculo().getMatricula(),
                venta.getVehiculo().getMarca(),
                venta.getVehiculo().getModelo(),
                venta.getVehiculo().getColor(),
                venta.getVehiculo().getPrecio()
        );
    }
    
}
