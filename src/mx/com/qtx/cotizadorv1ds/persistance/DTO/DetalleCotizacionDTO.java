package mx.com.qtx.cotizadorv1ds.persistance.DTO;

import java.math.BigDecimal;

public class DetalleCotizacionDTO {
	private int idDetalleCotizacion;
	private String descripcion;
    private int cantidad;
    private BigDecimal importeCotizado;
    private BigDecimal precioBase;
    private int idCotizacion;
    private int idComponente;
    private String sku;
    
	public String getSku() {
		return sku;
	}
	public void setSku(String sku) {
		this.sku = sku;
	}
	public int getIdDetalleCotizacion() {
		return idDetalleCotizacion;
	}
	public void setIdDetalleCotizacion(int idDetalleCotizacion) {
		this.idDetalleCotizacion = idDetalleCotizacion;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	public BigDecimal getImporteCotizado() {
		return importeCotizado;
	}
	public void setImporteCotizado(BigDecimal importeCotizado) {
		this.importeCotizado = importeCotizado;
	}
	public BigDecimal getPrecioBase() {
		return precioBase;
	}
	public void setPrecioBase(BigDecimal precioBase) {
		this.precioBase = precioBase;
	}
	public int getIdCotizacion() {
		return idCotizacion;
	}
	public void setIdCotizacion(int idCotizacion) {
		this.idCotizacion = idCotizacion;
	}
	public int getIdComponente() {
		return idComponente;
	}
	public void setIdComponente(int idComponente) {
		this.idComponente = idComponente;
	}
    
    
}
