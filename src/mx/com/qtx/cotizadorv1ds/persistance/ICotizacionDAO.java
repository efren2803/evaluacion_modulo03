package mx.com.qtx.cotizadorv1ds.persistance;

import java.sql.SQLException;
import java.util.List;

import mx.com.qtx.cotizadorv1ds.persistance.DTO.CotizacionDTO;
import mx.com.qtx.cotizadorv1ds.persistance.DTO.DetalleCotizacionDTO;

public interface ICotizacionDAO {
	int insertarConDetalle(CotizacionDTO cotizacion, List<DetalleCotizacionDTO> detalles) throws SQLException;
    CotizacionDTO buscarPorId(int idCotizacion) throws SQLException;
    void actualizar(CotizacionDTO cotizacion) throws SQLException;
    void eliminar(int idCotizacion) throws SQLException;
}
