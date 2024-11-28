package com.backend.concesionario.apirest.controllers;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.concesionario.apirest.entities.Vehiculo;
import com.backend.concesionario.apirest.services.VehiculoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("api/vehiculos")
public class VehiculoController {

    @Autowired
    private VehiculoService vehiculoService;

    // Método para obtener todos los vehículos
    @GetMapping
    public ResponseEntity<?> getAllVehiculos() {
        Iterable<Vehiculo> vehiculos = vehiculoService.findAll();
        if (!vehiculos.iterator().hasNext()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No hay vehículos.");
        }
        return ResponseEntity.ok(vehiculos);
    }

    // Método para obtener un vehículo por ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getVehiculoById(@PathVariable Long id) {
        Optional<Vehiculo> vehiculo = vehiculoService.findById(id);
        if (!vehiculo.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El vehículo con ID " + id + " no existe.");
        }
        return ResponseEntity.ok(vehiculo.get());
    }

    // Método para crear un nuevo vehículo
    @PostMapping("/create")
    public ResponseEntity<?> createCliente(@Valid @RequestBody Vehiculo vehiculo, BindingResult result) {
    	if (result.hasErrors()) {
            // Si hay errores de validación, devolvemos un mensaje claro
            Map<String, String> errors = new HashMap<>();
            result.getFieldErrors().forEach(error -> {
                errors.put(error.getField(), error.getDefaultMessage());
            });
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
        }
        try {
            // Guardar el nuevo vehículo
        	 Vehiculo nuevoVehiculo = vehiculoService.save(vehiculo);
            
            // Crear un map para enviar el mensaje de éxito junto con los datos del cliente
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Vehículo creado con éxito!");
            response.put("vehículo", nuevoVehiculo);
            
            // Devolver el status CREATED junto con el mensaje y los datos del cliente
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
          
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Collections.singletonMap("error", "Error al crear el vehículo: " + e.getMessage()));
        }
    }

    // Método para actualizar un vehículo por ID
    @PutMapping("/{id}")
    public ResponseEntity<?> updateVehiculo(@PathVariable Long id, @RequestBody Vehiculo vehiculo) {
        try {
            Optional<Vehiculo> vehiculoOptional = vehiculoService.findById(id);

            if (vehiculoOptional.isPresent()) {
                Vehiculo vehiculoDB = vehiculoOptional.get();

                // Actualizar los campos del vehículo
                vehiculoDB.setMatricula(vehiculo.getMatricula());
                vehiculoDB.setMarca(vehiculo.getMarca());
                vehiculoDB.setModelo(vehiculo.getModelo());
                vehiculoDB.setColor(vehiculo.getColor());
                vehiculoDB.setPrecio(vehiculo.getPrecio());

                return ResponseEntity.ok(vehiculoService.save(vehiculoDB));
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.singletonMap("error", "Vehículo no encontrado."));
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Collections.singletonMap("error", "Error al actualizar el vehículo: " + e.getMessage()));
        }
    }

    // Método para eliminar un vehículo por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteVehiculo(@PathVariable Long id) {
        try {
            Optional<Vehiculo> vehiculoOptional = vehiculoService.findById(id);

            if (vehiculoOptional.isPresent()) {
                vehiculoService.deleteById(id);
                return ResponseEntity.status(HttpStatus.OK).body(Collections.singletonMap("message", "Vehículo eliminado con éxito."));
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.singletonMap("error", "Vehículo no encontrado."));
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Collections.singletonMap("error", "Error al eliminar el vehículo: " + e.getMessage()));
        }
    }
}

