package mx.com.qtx.cotizadorv1ds.core.componentes;

import java.math.BigDecimal;

public class Otro extends ComponenteSimple {

	public Otro(String id, String descripcion, String marca, String modelo, BigDecimal costo, BigDecimal precioBase,
			String tipo) {
		super(id, descripcion, marca, modelo, costo, precioBase, tipo);
		
	}

	@Override
	public String getCategoria() {
		// TODO Auto-generated method stub
		return null;
	}
}
