package mx.com.qtx.cotizadorv1ds.core.componentes;

import java.math.BigDecimal;

public class Monitor extends ComponenteSimple {
	
	protected Monitor(String id, String descripcion, String marca, String modelo, BigDecimal costo,
			BigDecimal precioBase, String tipo) {
		super(id, descripcion, marca, modelo, costo, precioBase, tipo);
	}
	
	@Override
	public String getCategoria() {
		return "Monitor";
	}
}
