package mx.com.qtx.cotizadorv1ds.persistance;

import java.sql.SQLException;

import mx.com.qtx.cotizadorv1ds.persistance.DTO.ComponenteDTO;

public interface IComponenteDAO {
	int insertar(ComponenteDTO componente) throws SQLException;
	ComponenteDTO buscarPorSku(String Sku) throws SQLException;
    ComponenteDTO buscarPorId(int idComponente) throws SQLException;
    void actualizar(ComponenteDTO componente) throws SQLException;
    void eliminar(int idComponente) throws SQLException;
}
