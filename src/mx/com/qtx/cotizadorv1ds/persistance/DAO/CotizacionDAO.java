package mx.com.qtx.cotizadorv1ds.persistance.DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import mx.com.qtx.cotizadorv1ds.persistance.DatabaseConnector_MySQL;
import mx.com.qtx.cotizadorv1ds.persistance.ICotizacionDAO;
import mx.com.qtx.cotizadorv1ds.persistance.DTO.CotizacionDTO;
import mx.com.qtx.cotizadorv1ds.persistance.DTO.DetalleCotizacionDTO;

public class CotizacionDAO implements ICotizacionDAO {
	private Connection conexion;
    private DetalleCotizacionDAO detalleDAO;

    public CotizacionDAO() {
        try {
			this.conexion = DatabaseConnector_MySQL.getConnection();
			this.detalleDAO = new DetalleCotizacionDAO();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

	@Override
	public int insertarConDetalle(CotizacionDTO cotizacion, List<DetalleCotizacionDTO> detalles) throws SQLException {
		int idCotizacion = 0;
		try {
            conexion.setAutoCommit(false);
            // Insertar cotización
            String sql = "INSERT INTO Cotizacion (fechaCreacion, total, idCotizacion) VALUES (?, ?, ?)";
            try (PreparedStatement ps = conexion.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                ps.setDate(1, Date.valueOf(cotizacion.getFecha()));
                ps.setBigDecimal(2, cotizacion.getTotalCotizacion());
                ps.setInt(3, cotizacion.getIdCotizacion());
                ps.executeUpdate();
                
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    
    				if (rs.next()) idCotizacion = rs.getInt(1);
                    else throw new SQLException("No se generó ID para cotización");
                }
            }
            
            // Insertar detalles
            for (DetalleCotizacionDTO detalle : detalles) {
            	detalle.setIdCotizacion(idCotizacion);
                detalleDAO.insertar(detalle);
            }
            conexion.commit();
            return idCotizacion;
        } catch (SQLException e) {
            conexion.rollback();
            throw e;
        } finally {
            conexion.setAutoCommit(true);
        }
	}

	@Override
	public CotizacionDTO buscarPorId(int idCotizacion) throws SQLException {
	    String sql = "SELECT * FROM Cotizacion WHERE idCotizacion = ?";
	    CotizacionDTO cotizacion = new CotizacionDTO();
	    
	    try (PreparedStatement ps = conexion.prepareStatement(sql)) {
	        ps.setInt(1, idCotizacion);
	        ResultSet rs = ps.executeQuery();
	        
	        if (rs.next()) {
	            cotizacion.setFecha(rs.getDate("fecha").toLocalDate());
	            cotizacion.setTotalCotizacion(rs.getBigDecimal("totalCotizacion"));
	            cotizacion.setIdCotizacion(rs.getInt("idCotizacion"));
	        }
	    }
	    return cotizacion;
	}

	@Override
	public void actualizar(CotizacionDTO cotizacion) throws SQLException {
	    String sql = "UPDATE Cotizacion SET fecha=?, totalCotizacion=? WHERE idCotizacion=?";
	    try (PreparedStatement ps = conexion.prepareStatement(sql)) {
	        ps.setDate(1, Date.valueOf(cotizacion.getFecha()));
	        ps.setBigDecimal(2, cotizacion.getTotalCotizacion());
	        ps.setInt(3, cotizacion.getIdCotizacion());
	        ps.executeUpdate();
	    }
	}

	@Override
	public void eliminar(int idCotizacion) throws SQLException {
	    try {
	        conexion.setAutoCommit(false);
	        // Eliminar detalles primero
	        detalleDAO.eliminarPorCotizacion(idCotizacion);
	        
	        String sql = "DELETE FROM Cotizacion WHERE idCotizacion = ?";
	        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
	            ps.setInt(1, idCotizacion);
	            ps.executeUpdate();
	        }
	        conexion.commit();
	    } catch (SQLException e) {
	        conexion.rollback();
	        throw e;
	    } finally {
	        conexion.setAutoCommit(true);
	    }
	}
}
