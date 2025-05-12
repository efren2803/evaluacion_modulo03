package mx.com.qtx.cotizadorv1ds.persistance.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import mx.com.qtx.cotizadorv1ds.persistance.DatabaseConnector_MySQL;
import mx.com.qtx.cotizadorv1ds.persistance.IComponentePCDAO;
import mx.com.qtx.cotizadorv1ds.persistance.DTO.ComponentePCDTO;

public class ComponentePCDAO implements IComponentePCDAO {
	private Connection connection;

    public ComponentePCDAO() {
        try {
			this.connection = DatabaseConnector_MySQL.getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

	@Override
	public int insertar(ComponentePCDTO componentePC) throws SQLException {
String sql = "INSERT INTO ComponentePC (cantidad, idComponente, idPC) VALUES (?, ?, ?)";
        
        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, componentePC.getCantidad());
            stmt.setInt(2, componentePC.getIdComponente());
            stmt.setInt(3, componentePC.getIdPC());
            
            stmt.executeUpdate();
            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) return rs.getInt(1);
            }
        }
        return -1;
	}

	@Override
	public List<ComponentePCDTO> buscarPorId(int idPC) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean eliminar(int idComponentePC) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean actualizar(ComponentePCDTO componentePC) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}
    
    
}
