package mx.com.qtx.cotizadorv1ds.persistance;

import java.sql.SQLException;
import java.util.List;

import mx.com.qtx.cotizadorv1ds.persistance.DTO.DetalleCotizacionDTO;

public interface IDetalleCotizacionDAO {
	void insertar(DetalleCotizacionDTO detalle) throws SQLException;
    List<DetalleCotizacionDTO> buscarPorCotizacion(int idCotizacion) throws SQLException;
    void eliminar(int idDetalle) throws SQLException;
    void eliminarPorCotizacion(int idCotizacion) throws SQLException;
}
