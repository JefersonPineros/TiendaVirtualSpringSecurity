package co.com.tienda.virtual.back.api.services;

import java.util.List;

import org.springframework.dao.DataAccessException;

import co.com.tienda.virtual.back.api.entity.dto.FacturaDto;
import co.com.tienda.virtual.back.api.entity.models.Factura;

public interface IFacturaService {

	public List<FacturaDto> findAll();
	
	public FacturaDto findById(Integer id);
	
	public FacturaDto createFactura(Factura factura) throws DataAccessException;
	
	public FacturaDto updateFactura(Factura factura) throws DataAccessException;
	
	public void changeState(Factura factura);
}
