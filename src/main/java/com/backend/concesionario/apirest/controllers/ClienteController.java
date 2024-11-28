package com.backend.concesionario.apirest.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.backend.concesionario.apirest.entities.Cliente;
import com.backend.concesionario.apirest.services.ClienteService;

import jakarta.validation.Valid;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("api/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    // Método para obtener todos los clientes
    @GetMapping
    public ResponseEntity<?> getAllClientes() {
        Iterable<Cliente> clientes = clienteService.findAll();
        if (!clientes.iterator().hasNext()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No hay clientes.");
        }
        return ResponseEntity.ok(clientes);
    }

    // Método para obtener un cliente por ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getClienteById(@PathVariable Long id) {
        Optional<Cliente> cliente = clienteService.findById(id);
        if (!cliente.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El cliente con ID " + id + " no existe.");
        }
        return ResponseEntity.ok(cliente.get());
    }

    // Método para crear un nuevo cliente
    @PostMapping("/create")
    public ResponseEntity<?> createCliente(@Valid @RequestBody Cliente cliente, BindingResult result) {
    	if (result.hasErrors()) {
            // Si hay errores de validación, devolvemos un mensaje claro
            Map<String, String> errors = new HashMap<>();
            result.getFieldErrors().forEach(error -> {
                errors.put(error.getField(), error.getDefaultMessage());
            });
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
        }
        try {
            // Guardar el nuevo cliente
            Cliente nuevoCliente = clienteService.save(cliente);
            
            // Crear un map para enviar el mensaje de éxito junto con los datos del cliente
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Cliente creado con éxito!");
            response.put("cliente", nuevoCliente);
            
            // Devolver el status CREATED junto con el mensaje y los datos del cliente
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
          
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Collections.singletonMap("error", "Error al crear el cliente: " + e.getMessage()));
        }
    }

    // Método para actualizar un cliente por ID
    @PutMapping("/{id}")
    public ResponseEntity<?> updateCliente(@PathVariable Long id, @RequestBody Cliente cliente) {
        try {
            // Buscar si el cliente con el ID proporcionado existe
            Optional<Cliente> clienteOptional = clienteService.findById(id);
            
            if (clienteOptional.isPresent()) {
                // Obtener el cliente existente
                Cliente clienteDB = clienteOptional.get();
                
                // Actualizar los campos del cliente
                clienteDB.setNombre(cliente.getNombre());
                clienteDB.setApellidos(cliente.getApellidos());
                clienteDB.setDireccion(cliente.getDireccion());
                clienteDB.setTelefono(cliente.getTelefono());
                clienteDB.setEmail(cliente.getEmail());
                
                // Guardar los cambios
                return ResponseEntity.ok(clienteService.save(clienteDB));
            } else {
                // Si no se encuentra el cliente, devolver mensaje de error
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.singletonMap("error", "Cliente no encontrado."));
            }
        } catch (Exception e) {
            // Manejar cualquier excepción que ocurra durante la actualización
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Collections.singletonMap("error", "Error al actualizar el cliente: " + e.getMessage()));
        }
    }


    // Método para eliminar un cliente por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCliente(@PathVariable Long id) {
        try {
            // Buscar si el cliente con el ID proporcionado existe
            Optional<Cliente> clienteOptional = clienteService.findById(id);
            
            if (clienteOptional.isPresent()) {
                // Si existe, eliminar el cliente
                clienteService.deleteById(id);
                return ResponseEntity.status(HttpStatus.OK).body(Collections.singletonMap("message", "Cliente eliminado con exito."));
            } else {
                // Si no se encuentra el cliente, devolver un mensaje de error
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.singletonMap("error", "Cliente no encontrado."));
            }
        } catch (Exception e) {
            // Manejar cualquier excepción que ocurra durante la eliminación
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Collections.singletonMap("error", "Error al eliminar el cliente: " + e.getMessage()));
        }
    }

}

