package mx.com.qtx.cotizadorv1ds.persistance.DTO;

import java.math.BigDecimal;
import java.time.LocalDate;

public class CotizacionDTO {
	private LocalDate fecha;
	private String cliente;
    private BigDecimal totalCotizacion;
    private int idCotizacion;
    
	public LocalDate getFecha() {
		return fecha;
	}
	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}
	public BigDecimal getTotalCotizacion() {
		return totalCotizacion;
	}
	public void setTotalCotizacion(BigDecimal totalCotizacion) {
		this.totalCotizacion = totalCotizacion;
	}
	public int getIdCotizacion() {
		return idCotizacion;
	}
	public void setIdCotizacion(int idCotizacion) {
		this.idCotizacion = idCotizacion;
	}
	public String getCliente() {
		return cliente;
	}
	public void setCliente(String cliente) {
		this.cliente = cliente;
	}

    
}
