package mx.com.qtx.cotizadorv1ds.cotizadorB;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mx.com.qtx.cotizadorv1ds.core.ComponenteInvalidoException;
import mx.com.qtx.cotizadorv1ds.core.Cotizacion;
import mx.com.qtx.cotizadorv1ds.core.DetalleCotizacion;
import mx.com.qtx.cotizadorv1ds.core.ICotizador;
import mx.com.qtx.cotizadorv1ds.core.componentes.Componente;
import mx.com.qtx.cotizadorv1ds.persistance.GestorDatosCotizacion;
import mx.com.qtx.cotizadorv1ds.persistance.DTO.CotizacionDTO;
import mx.com.qtx.cotizadorv1ds.persistance.DTO.DetalleCotizacionDTO;

public class CotizadorConMap implements ICotizador {
	private Map<Componente,Integer> mapCompsYcants;

	public CotizadorConMap() {
		super();
		this.mapCompsYcants = new HashMap<>();
	}

	@Override
	public void agregarComponente(int cantidad, Componente componente) {
		this.mapCompsYcants.put(componente, cantidad);
	}

	@Override
	public void eliminarComponente(String idComponente) throws ComponenteInvalidoException {
		try {
    	   Componente llave = this.mapCompsYcants.keySet()
    			                    .stream()
    			                    .filter(k->k.getId().equals(idComponente))
    			                     .findFirst()
    			                     .get();
               
		
    	   this.mapCompsYcants.remove(llave);
		}
		catch(Exception ex) {
			throw new ComponenteInvalidoException("Error subyacente", ex, null);
		}
	}

	@Override
	public Cotizacion generarCotizacion() {
        BigDecimal total = new BigDecimal(0);
        
        GestorDatosCotizacion gestor = new GestorDatosCotizacion();
        int idSubComponentePc = 0;
		Componente subComponenteEncontrado;
        
        CotizacionDTO cotizacionBD = new CotizacionDTO();
        DetalleCotizacionDTO componenteDetalle;
        List<DetalleCotizacionDTO> listaComponentes = new ArrayList<DetalleCotizacionDTO>();
        
        Cotizacion cotizacion = new CotizacionFmtoB();
        int i=0;
        for(Componente compI:this.mapCompsYcants.keySet()) {
        	int cantidadI = this.mapCompsYcants.get(compI);
        	BigDecimal importeCotizadoI = new BigDecimal(0);
        	i++;
        	
        	importeCotizadoI = compI.cotizar(cantidadI);
        	        
        	DetalleCotizacion detI = new DetalleCotizacion((i), compI.getId(), compI.getDescripcion(), cantidadI, 
        			                                        compI.getPrecioBase(), importeCotizadoI, compI.getCategoria());
        	cotizacion.agregarDetalle(detI);
        	
        	componenteDetalle = new DetalleCotizacionDTO();
        	componenteDetalle.setDescripcion(compI.getDescripcion());
        	componenteDetalle.setCantidad(cantidadI);
        	componenteDetalle.setImporteCotizado(importeCotizadoI);
        	componenteDetalle.setPrecioBase(compI.getPrecioBase());
        	
        	subComponenteEncontrado = gestor.buscarPorSku(compI.getId());
			idSubComponentePc = Integer.parseInt(subComponenteEncontrado.getId());
			componenteDetalle.setIdComponente(idSubComponentePc);
			componenteDetalle.setSku(compI.getId());
			
			listaComponentes.add(componenteDetalle);
        	
            total = total.add(importeCotizadoI);
        }
        cotizacion.setTotal(total);
        
        cotizacionBD.setFecha(LocalDate.now());
        cotizacionBD.setCliente("Cotizacion Sin Cliente");
        cotizacionBD.setTotalCotizacion(total);
        
        gestor.agregarCotizacionConDetalle(cotizacionBD, listaComponentes);
   	
		return cotizacion;
	}

	@Override
	public void listarComponentes() {
        System.out.println("=== Componentes a cotizar en CotizadorConMap ===");
        for(Componente compI:this.mapCompsYcants.keySet())  {
        	int cantidad = this.mapCompsYcants.get(compI);
            System.out.println(cantidad + " " + compI.getDescripcion() 
            		 + ": $" + compI.getPrecioBase() + " ID:" + compI.getId());        	
        }
	}

}
