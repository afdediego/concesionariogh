package com.backend.concesionario.apirest.controllers;

import java.util.Collections;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.concesionario.apirest.entities.Venta;
import com.backend.concesionario.apirest.services.VentaService;

@RestController
@RequestMapping("api/ventas")
public class VentaController {

    @Autowired
    private VentaService ventaService;

    // Método para obtener todas las ventas
    @GetMapping
    public ResponseEntity<?> getAllVentas() {
        Iterable<Venta> ventas = ventaService.findAll();
        if (!ventas.iterator().hasNext()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No hay ventas.");
        }
        return ResponseEntity.ok(ventas);
    }

    // Método para obtener una venta por ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getVentaById(@PathVariable Long id) {
        Optional<Venta> venta = ventaService.findById(id);
        if (!venta.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("La venta con ID " + id + " no existe.");
        }
        return ResponseEntity.ok(venta.get());
    }

    // Método para crear una nueva venta
    @PostMapping("/create")
    public ResponseEntity<?> createVenta(@RequestBody Venta venta) {
        try {
            Venta nuevaVenta = ventaService.save(venta);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevaVenta);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Collections.singletonMap("error", "Error al crear la venta: " + e.getMessage()));
        }
    }

    // Método para actualizar una venta por ID
    @PutMapping("/{id}")
    public ResponseEntity<?> updateVenta(@PathVariable Long id, @RequestBody Venta venta) {
        try {
            Optional<Venta> ventaOptional = ventaService.findById(id);

            if (ventaOptional.isPresent()) {
                Venta ventaDB = ventaOptional.get();

                // Actualizar los campos de la venta
                ventaDB.setFechaVenta(venta.getFechaVenta());
                ventaDB.setCliente(venta.getCliente());
                ventaDB.setVehiculo(venta.getVehiculo());

                return ResponseEntity.ok(ventaService.save(ventaDB));
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.singletonMap("error", "Venta no encontrada."));
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Collections.singletonMap("error", "Error al actualizar la venta: " + e.getMessage()));
        }
    }

    // Método para eliminar una venta por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteVenta(@PathVariable Long id) {
        try {
            Optional<Venta> ventaOptional = ventaService.findById(id);

            if (ventaOptional.isPresent()) {
                ventaService.deleteById(id);
                return ResponseEntity.status(HttpStatus.OK).body(Collections.singletonMap("message", "Venta eliminada con éxito."));
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.singletonMap("error", "Venta no encontrada."));
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Collections.singletonMap("error", "Error al eliminar la venta: " + e.getMessage()));
        }
    }
}
