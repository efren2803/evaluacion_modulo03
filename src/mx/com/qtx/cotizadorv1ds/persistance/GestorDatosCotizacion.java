package mx.com.qtx.cotizadorv1ds.persistance;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

import mx.com.qtx.cotizadorv1ds.core.componentes.Componente;
import mx.com.qtx.cotizadorv1ds.persistance.DAO.ComponenteDAO;
import mx.com.qtx.cotizadorv1ds.persistance.DAO.ComponentePCDAO;
import mx.com.qtx.cotizadorv1ds.persistance.DAO.CotizacionDAO;
import mx.com.qtx.cotizadorv1ds.persistance.DTO.ComponenteDTO;
import mx.com.qtx.cotizadorv1ds.persistance.DTO.ComponentePCDTO;
import mx.com.qtx.cotizadorv1ds.persistance.DTO.CotizacionDTO;
import mx.com.qtx.cotizadorv1ds.persistance.DTO.DetalleCotizacionDTO;

public class GestorDatosCotizacion {

	private ComponenteDAO compDAO = new ComponenteDAO();
	private ComponentePCDAO compPcDAO = new ComponentePCDAO();
		
	public GestorDatosCotizacion() {
		super();
	}
	
	public int agregarComponenteSimpleADB(String id, String descripcion, String marca, String modelo, 
            BigDecimal costo, BigDecimal precioBase, String tipo) {
		int resultIns = 0;
		
    	ComponenteDTO compDTO = new ComponenteDTO();
    	compDTO.setDescripcion(descripcion);
    	compDTO.setMarca(marca);
    	compDTO.setModelo(modelo);
    	compDTO.setCosto(costo);
    	compDTO.setPrecioBase(precioBase);
    	compDTO.setTipo(tipo);
    	compDTO.setSku(id);
    	
    	try {
			resultIns = compDAO.insertar(compDTO);
			System.out.println("Componente insertado con el id: " + resultIns);
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	
    	return resultIns;
	}

	public int agregarDiscoDuroADB(String id, String descripcion, String marca, String modelo, BigDecimal costo,
			BigDecimal precioBase, String capacidadAlm, String tipo) {
		
		int resultIns = 0;
		
		ComponenteDTO compDTO = new ComponenteDTO();
    	compDTO.setDescripcion(descripcion);
    	compDTO.setMarca(marca);
    	compDTO.setModelo(modelo);
    	compDTO.setCosto(costo);
    	compDTO.setPrecioBase(precioBase);
    	compDTO.setTipo(tipo);
    	compDTO.setCapacidadAlmacenamiento(capacidadAlm);
    	compDTO.setSku(id);
    	
    	try {
			resultIns = compDAO.insertar(compDTO);
			System.out.println("Componente insertado con el id: " + resultIns);
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	
    	return resultIns;
	}
	
	public int agregarTarjetaVideoADB(String id, String descripcion, String marca, String modelo, BigDecimal costo,
			BigDecimal precioBase, String memoria, String tipo) {
		
		int resultIns = 0;
		
		ComponenteDTO compDTO = new ComponenteDTO();
    	compDTO.setDescripcion(descripcion);
    	compDTO.setMarca(marca);
    	compDTO.setModelo(modelo);
    	compDTO.setCosto(costo);
    	compDTO.setPrecioBase(precioBase);
    	compDTO.setTipo(tipo);
    	compDTO.setMemoriaTarjetaVideo(memoria);
    	compDTO.setSku(id);
    	
    	try {
			resultIns = compDAO.insertar(compDTO);
			System.out.println("Componente insertado con el id: " + resultIns);
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	
    	return resultIns;
	}
	
	public Componente buscarPorSku(String sku) {
		ComponenteDTO compDto;
		Componente resultComponente;
		try {
			compDto = compDAO.buscarPorSku(sku);
			
			switch (compDto.getTipo()) {
			case "Disco duro":
				resultComponente = Componente.obtenerDiscoDuro(String.valueOf(compDto.getIdComponente()),compDto.getDescripcion(),compDto.getMarca(),compDto.getModelo(),compDto.getCosto(),compDto.getPrecioBase(),compDto.getCapacidadAlmacenamiento(),compDto.getTipo());
				break;
			case "Tarjeta de video":
				resultComponente = Componente.obtenerTarjetaVideo(String.valueOf(compDto.getIdComponente()),compDto.getDescripcion(),compDto.getMarca(),compDto.getModelo(),compDto.getCosto(),compDto.getPrecioBase(),compDto.getMemoriaTarjetaVideo(),compDto.getTipo());
				break;
			case "Monitor":
				resultComponente = Componente.obtenerMonitor(String.valueOf(compDto.getIdComponente()),compDto.getDescripcion(),compDto.getMarca(),compDto.getModelo(),compDto.getCosto(),compDto.getPrecioBase(),compDto.getTipo());
				break;
			default:
				resultComponente = Componente.obtenerComponenteSimple(String.valueOf(compDto.getIdComponente()),compDto.getDescripcion(),compDto.getMarca(),compDto.getModelo(),compDto.getCosto(),compDto.getPrecioBase(),compDto.getTipo());
				break;
			}
			return resultComponente;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public int agregarPcADB(String id, String descripcion, String marca, String modelo, List<Componente> subComponentes,String tipo)
	{
		int idPc = 0;
		int idSubComponentePc = 0;
		Componente subComponenteEncontrado;
		ComponentePCDTO compPc;
		
		BigDecimal costoBd = new BigDecimal(0);
		BigDecimal precioBasebBd = new BigDecimal(0);
		idPc = agregarComponenteSimpleADB(id,descripcion,marca,modelo,costoBd, precioBasebBd, tipo);
		
		for (Componente componente : subComponentes) 
		{
			subComponenteEncontrado = buscarPorSku(componente.getId());
			idSubComponentePc = Integer.parseInt(subComponenteEncontrado.getId()); 
			
			compPc = new ComponentePCDTO();
			compPc.setCantidad(1);
			compPc.setIdComponente(idSubComponentePc);
			compPc.setIdPC(idPc);
			
			try {
				compPcDAO.insertar(compPc);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return idPc;
	}
	
	public int agregarCotizacionConDetalle(CotizacionDTO cotizacion, List<DetalleCotizacionDTO> detalles) {
		int idCotizacion = 0;
		
		CotizacionDAO cotiza = new CotizacionDAO();
		try {
			cotiza.insertarConDetalle(cotizacion, detalles);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return idCotizacion;
	}
}
