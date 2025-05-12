package mx.com.qtx.cotizadorv1ds.persistance.DTO;

import java.math.BigDecimal;

public class ComponenteDTO {
	private int idComponente;
    private String descripcion;
    private String marca;
    private String modelo;
    private BigDecimal costo;
    private BigDecimal precioBase;
    private String tipo;
    private String memoriaTarjetaVideo; // Solo para tipo "Tarjeta Video"
    private String capacidadAlmacenamiento; // Solo para tipo "Disco Duro"
    private String sku;
    
	public String getSku() {
		return sku;
	}
	public void setSku(String sku) {
		this.sku = sku;
	}
	public int getIdComponente() {
		return idComponente;
	}
	public void setIdComponente(int idComponente) {
		this.idComponente = idComponente;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
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
	public BigDecimal getCosto() {
		return costo;
	}
	public void setCosto(BigDecimal costo) {
		this.costo = costo;
	}
	public BigDecimal getPrecioBase() {
		return precioBase;
	}
	public void setPrecioBase(BigDecimal precioBase) {
		this.precioBase = precioBase;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getMemoriaTarjetaVideo() {
		return memoriaTarjetaVideo;
	}
	public void setMemoriaTarjetaVideo(String memoriaTarjetaVideo) {
		this.memoriaTarjetaVideo = memoriaTarjetaVideo;
	}
	public String getCapacidadAlmacenamiento() {
		return capacidadAlmacenamiento;
	}
	public void setCapacidadAlmacenamiento(String capacidadAlmacenamiento) {
		this.capacidadAlmacenamiento = capacidadAlmacenamiento;
	}
}
