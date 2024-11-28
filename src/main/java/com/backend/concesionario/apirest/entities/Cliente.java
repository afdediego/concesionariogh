package com.backend.concesionario.apirest.entities;
import java.io.Serializable;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "clientes")
public class Cliente implements Serializable {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

	@NotEmpty(message = "El nombre es obligatorio")
	@Size(max = 100, message = "El nombre no puede exceder los 100 caracteres")
    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;

	@NotEmpty(message = "Los apellidos son obligatorios")
	@Size(max = 100, message = "Los apellidos no pueden exceder los 100 caracteres")
    @Column(name = "apellidos", nullable = false, length = 100)
    private String apellidos;

	@NotEmpty(message = "La dirección es obligatoria")
	@Size(max = 100, message = "La dirección no puede exceder los 100 caracteres")
    @Column(name = "direccion", nullable = false, length = 100)
    private String direccion;

	@NotEmpty(message = "El teléfono es obligatorio")
	@Size(max = 15, message = "El teléfono no puede exceder los 15 caracteres")
    @Column(name = "telefono", nullable = false, length = 15)
    private String telefono;

	@NotEmpty(message = "El email es obligatorio")
    @Email(message = "El email debe ser válido")
    @Column(name = "email", nullable = false, length = 100, unique = true) // Establecer como único
    private String email;

    // Relación uno a muchos: un cliente puede haber comprado muchos coches
    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference // Indica que esta parte de la relación debe ser serializada
    private List<Venta> vendidos; 

    public Cliente() {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<Venta> getVendidos() {
		return vendidos;
	}

	public void setVendidos(List<Venta> vendidos) {
		this.vendidos = vendidos;
	}


	/**
   	 * 
   	 */
   	private static final long serialVersionUID = 1L;
    
  
}

