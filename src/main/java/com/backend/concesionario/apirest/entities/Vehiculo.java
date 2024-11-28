package com.backend.concesionario.apirest.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonBackReference;


@Entity
@Table(name = "vehiculos")
public class Vehiculo implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "La matrícula es obligatorio")
	@Size(min = 7, message = "La matrícula tiene que tener un minimo de 7 caracteres")
    @Column(name = "matricula", nullable = false, unique = true, length = 10)
    private String matricula;

    @NotEmpty(message = "La marca es obligatorio")
   	@Size(max = 50, message = "La marca no puede exceder los 50 caracteres")
    @Column(name = "marca", nullable = false, length = 50)
    private String marca;

    @NotEmpty(message = "El modelo es obligatorio")
   	@Size(max = 50, message = "El modelo no puede exceder los 50 caracteres")
    @Column(name = "modelo", nullable = false, length = 50)
    private String modelo;

    @NotEmpty(message = "El color es obligatorio")
   	@Size(max = 50, message = "El color no puede exceder los 50 caracteres")
    @Column(name = "color", nullable = false, length = 50)
    private String color;

  
    @Column(name = "precio", nullable = false)
    private Double precio;

    @OneToOne(mappedBy = "vehiculo") // Relación uno a uno con "Vendido"
    @JsonBackReference // Evita la serialización inversa de la venta
    private Venta venta; // Relación uno a uno: un vehículo solo puede ser vendido una vez

    public Vehiculo() {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public Double getPrecio() {
		return precio;
	}

	public void setPrecio(Double precio) {
		this.precio = precio;
	}

	public Venta getVenta() {
		return venta;
	}

	public void setVenta(Venta venta) {
		this.venta = venta;
	}
    
	/**
 	 * 
 	 */
 	private static final long serialVersionUID = 1L;
 	
}