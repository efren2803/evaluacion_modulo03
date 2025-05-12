package mx.com.qtx.cotizadorv1ds.persistance.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import mx.com.qtx.cotizadorv1ds.persistance.DatabaseConnector_MySQL;
import mx.com.qtx.cotizadorv1ds.persistance.IDetalleCotizacionDAO;
import mx.com.qtx.cotizadorv1ds.persistance.DTO.DetalleCotizacionDTO;

public class DetalleCotizacionDAO implements IDetalleCotizacionDAO {
	private Connection conexion;

    public DetalleCotizacionDAO() throws SQLException {
        this.conexion = DatabaseConnector_MySQL.getConnection();
    }

	@Override
	public void insertar(DetalleCotizacionDTO detalle) throws SQLException {
		String sql = "INSERT INTO DetalleCotizacion (description, cantidad, importeCotizado, precioBase, idCotizacion, idComponente, sku) VALUES (?, ?, ?, ?, ?, ?, ?)";
		
	    try (PreparedStatement ps = conexion.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) 
	     {
	         ps.setString(1, detalle.getDescripcion());
	         ps.setInt(2, detalle.getCantidad());
	         ps.setBigDecimal(3, detalle.getImporteCotizado());
	         ps.setBigDecimal(4, detalle.getPrecioBase());
	         ps.setInt(5, detalle.getIdCotizacion());
	         ps.setInt(6, detalle.getIdComponente());
	         ps.setString(7, detalle.getSku());
	         
	         ps.executeUpdate();
	         
	         try (ResultSet rs = ps.getGeneratedKeys()) 
	         {
	             if (rs.next()) 
	            	 System.out.println(rs.getInt(1)); 
	         }
	     }
	 }

	@Override
	public List<DetalleCotizacionDTO> buscarPorCotizacion(int idCotizacion) throws SQLException {
	    String sql = "SELECT * FROM DetalleCotizacion WHERE idCotizacion = ?";
	    List<DetalleCotizacionDTO> detalles = new ArrayList<>();
	    
	    try (PreparedStatement ps = conexion.prepareStatement(sql)) {
	        ps.setInt(1, idCotizacion);
	        ResultSet rs = ps.executeQuery();
	        
	        while (rs.next()) {
	            DetalleCotizacionDTO detalle = new DetalleCotizacionDTO();
	            detalle.setIdDetalleCotizacion(rs.getInt("idDetalleCotizacion"));
	            detalle.setDescripcion(rs.getString("descripcion"));
	            detalle.setCantidad(rs.getInt("cantidad"));
	            detalle.setImporteCotizado(rs.getBigDecimal("importeCotizado"));
	            detalle.setPrecioBase(rs.getBigDecimal("precioBase"));
	            detalle.setIdCotizacion(rs.getInt("idCotizacion"));
	            detalle.setIdComponente(rs.getInt("idComponente"));
	            detalles.add(detalle);
	        }
	    }
	    return detalles;
	}

	@Override
	public void eliminar(int idDetalle) throws SQLException {
	    String sql = "DELETE FROM DetalleCotizacion WHERE idDetalleCotizacion = ?";
	    try (PreparedStatement ps = conexion.prepareStatement(sql)) {
	        ps.setInt(1, idDetalle);
	        ps.executeUpdate();
	    }
	}

	@Override
	public void eliminarPorCotizacion(int idCotizacion) throws SQLException {
	    String sql = "DELETE FROM DetalleCotizacion WHERE idCotizacion = ?";
	    try (PreparedStatement ps = conexion.prepareStatement(sql)) {
	        ps.setInt(1, idCotizacion);
	        ps.executeUpdate();
	    }
	}
}
