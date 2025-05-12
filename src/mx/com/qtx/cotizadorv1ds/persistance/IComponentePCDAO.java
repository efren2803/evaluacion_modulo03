package mx.com.qtx.cotizadorv1ds.persistance;

import java.sql.SQLException;
import java.util.List;

import mx.com.qtx.cotizadorv1ds.persistance.DTO.ComponentePCDTO;

public interface IComponentePCDAO {
	int insertar(ComponentePCDTO componentePC) throws SQLException;
    List<ComponentePCDTO> buscarPorId(int idPC) throws SQLException;
    boolean eliminar(int idComponentePC) throws SQLException;
    boolean actualizar(ComponentePCDTO componentePC) throws SQLException;
}
