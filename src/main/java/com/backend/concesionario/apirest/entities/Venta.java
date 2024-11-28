package com.backend.concesionario.apirest.entities;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "ventas")
public class Venta implements Serializable {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "vehiculo_id", nullable = false)
    @JsonManagedReference // Para serializar el vehículo
    private Vehiculo vehiculo; // Relación uno a uno con Vehiculo: un coche vendido

    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    @JsonBackReference // Evita la serialización inversa del cliente
    private Cliente cliente; // Relación muchos a uno: un cliente puede haber comprado muchos coches

    @Column(name = "fecha_venta", nullable = false, length = 100)
    private String fechaVenta;

    public Venta() {
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Vehiculo getVehiculo() {
		return vehiculo;
	}

	public void setVehiculo(Vehiculo vehiculo) {
		this.vehiculo = vehiculo;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public String getFechaVenta() {
		return fechaVenta;
	}

	public void setFechaVenta(String fechaVenta) {
		this.fechaVenta = fechaVenta;
	}
    
   /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
}
