package mx.com.qtx.cotizadorv1ds.cotizadorA;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import mx.com.qtx.cotizadorv1ds.core.ComponenteInvalidoException;
import mx.com.qtx.cotizadorv1ds.core.Cotizacion;
import mx.com.qtx.cotizadorv1ds.core.DetalleCotizacion;
import mx.com.qtx.cotizadorv1ds.core.ICotizador;
import mx.com.qtx.cotizadorv1ds.core.componentes.Componente;
import mx.com.qtx.cotizadorv1ds.persistance.GestorDatosCotizacion;
import mx.com.qtx.cotizadorv1ds.persistance.DAO.ComponenteDAO;
import mx.com.qtx.cotizadorv1ds.persistance.DTO.ComponenteDTO;
import mx.com.qtx.cotizadorv1ds.persistance.DTO.CotizacionDTO;
import mx.com.qtx.cotizadorv1ds.persistance.DTO.DetalleCotizacionDTO;

public class Cotizador implements ICotizador{
    private List<Componente> componentes = new ArrayList<>();
    private List<Integer> cantidades = new ArrayList<>();
    
    private ComponenteDAO compDAO = new ComponenteDAO();

    // Métodos de gestión de componentes
    public void agregarComponente(int cantidad, Componente componente) {
    	this.cantidades.add(cantidad);
    	this.componentes.add(componente);
    }

    public void eliminarComponente(String idComponente) throws ComponenteInvalidoException {
    	if(idComponente == null) {
    		throw new ComponenteInvalidoException("Id del componente es nulo ", null);
    	}
    	int i = this.componentes.stream().map(compI->compI.getId())
    			                         .toList().indexOf(idComponente);
    	if (i==-1) {// NO existe
    		throw new ComponenteInvalidoException("No existe componente con Id "+ idComponente, null);
    	}
    	this.cantidades.remove(i);
    	this.componentes.remove(i);
    }

    public Cotizacion generarCotizacion() {
        BigDecimal total = new BigDecimal(0);
        
        GestorDatosCotizacion gestor = new GestorDatosCotizacion();
        int idSubComponentePc = 0;
		Componente subComponenteEncontrado;
        
        CotizacionDTO cotizacionBD = new CotizacionDTO();
        DetalleCotizacionDTO componenteDetalle;
        List<DetalleCotizacionDTO> listaComponentes = new ArrayList<DetalleCotizacionDTO>();
        
        Cotizacion cotizacion = new Cotizacion();
        
        for(int i=0; i<this.cantidades.size();i++) {
        	Componente compI = this.componentes.get(i);
        	int cantidadI = this.cantidades.get(i);
        	BigDecimal importeCotizadoI = new BigDecimal(0);
        	
        	importeCotizadoI = compI.cotizar(cantidadI);
        	        
        	DetalleCotizacion detI = new DetalleCotizacion((i + 1), compI.getId(), compI.getDescripcion(), cantidadI, 
        			                                        compI.getPrecioBase(), importeCotizadoI, compI.getCategoria());
        	
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
        	
        	cotizacion.agregarDetalle(detI);
            total = total.add(importeCotizadoI);
        }
        cotizacion.setTotal(total);
        
        cotizacionBD.setFecha(LocalDate.now());
        cotizacionBD.setCliente("Cotizacion Sin Cliente");
        cotizacionBD.setTotalCotizacion(total);
        
        gestor.agregarCotizacionConDetalle(cotizacionBD, listaComponentes);
   	
		return cotizacion;
    }

	public void listarComponentes() {
        System.out.println("=== Componentes a cotizar ===");
        for(int i=0; i<this.cantidades.size();i++) {
        	Componente c = this.componentes.get(i);
            System.out.println(this.cantidades.get(i) + " " + c.getDescripcion() 
            		 + ": $" + c.getPrecioBase() + " ID:" + c.getId());        	
        }
    }

}