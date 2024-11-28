package com.backend.concesionario.apirest.dtos;

public class VentaDTO {

	private Long ventaId;
    private String fechaVenta;

    // Detalles del cliente
    private String clienteNombre;
    private String clienteApellidos;
    private String clienteDireccion;
    private String clienteTelefono;
    private String clienteEmail;

    // Detalles del vehículo
    private String vehiculoMatricula;
    private String vehiculoMarca;
    private String vehiculoModelo;
    private String vehiculoColor;
    private Double vehiculoPrecio;
    
	public VentaDTO(Long ventaId, String fechaVenta, String clienteNombre, String clienteApellidos,
			String clienteDireccion, String clienteTelefono, String clienteEmail, String vehiculoMatricula,
			String vehiculoMarca, String vehiculoModelo, String vehiculoColor, Double vehiculoPrecio) {
		this.ventaId = ventaId;
		this.fechaVenta = fechaVenta;
		this.clienteNombre = clienteNombre;
		this.clienteApellidos = clienteApellidos;
		this.clienteDireccion = clienteDireccion;
		this.clienteTelefono = clienteTelefono;
		this.clienteEmail = clienteEmail;
		this.vehiculoMatricula = vehiculoMatricula;
		this.vehiculoMarca = vehiculoMarca;
		this.vehiculoModelo = vehiculoModelo;
		this.vehiculoColor = vehiculoColor;
		this.vehiculoPrecio = vehiculoPrecio;
	}

	public Long getVentaId() {
		return ventaId;
	}

	public void setVentaId(Long ventaId) {
		this.ventaId = ventaId;
	}

	public String getFechaVenta() {
		return fechaVenta;
	}

	public void setFechaVenta(String fechaVenta) {
		this.fechaVenta = fechaVenta;
	}

	public String getClienteNombre() {
		return clienteNombre;
	}

	public void setClienteNombre(String clienteNombre) {
		this.clienteNombre = clienteNombre;
	}

	public String getClienteApellidos() {
		return clienteApellidos;
	}

	public void setClienteApellidos(String clienteApellidos) {
		this.clienteApellidos = clienteApellidos;
	}

	public String getClienteDireccion() {
		return clienteDireccion;
	}

	public void setClienteDireccion(String clienteDireccion) {
		this.clienteDireccion = clienteDireccion;
	}

	public String getClienteTelefono() {
		return clienteTelefono;
	}

	public void setClienteTelefono(String clienteTelefono) {
		this.clienteTelefono = clienteTelefono;
	}

	public String getClienteEmail() {
		return clienteEmail;
	}

	public void setClienteEmail(String clienteEmail) {
		this.clienteEmail = clienteEmail;
	}

	public String getVehiculoMatricula() {
		return vehiculoMatricula;
	}

	public void setVehiculoMatricula(String vehiculoMatricula) {
		this.vehiculoMatricula = vehiculoMatricula;
	}

	public String getVehiculoMarca() {
		return vehiculoMarca;
	}

	public void setVehiculoMarca(String vehiculoMarca) {
		this.vehiculoMarca = vehiculoMarca;
	}

	public String getVehiculoModelo() {
		return vehiculoModelo;
	}

	public void setVehiculoModelo(String vehiculoModelo) {
		this.vehiculoModelo = vehiculoModelo;
	}

	public String getVehiculoColor() {
		return vehiculoColor;
	}

	public void setVehiculoColor(String vehiculoColor) {
		this.vehiculoColor = vehiculoColor;
	}

	public Double getVehiculoPrecio() {
		return vehiculoPrecio;
	}

	public void setVehiculoPrecio(Double vehiculoPrecio) {
		this.vehiculoPrecio = vehiculoPrecio;
	}

}
