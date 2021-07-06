package co.com.tienda.virtual.back.api.services;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.com.tienda.virtual.back.api.entity.dao.IFacturaDao;
import co.com.tienda.virtual.back.api.entity.dao.IProductosFacturaDao;
import co.com.tienda.virtual.back.api.entity.dto.FacturaDto;
import co.com.tienda.virtual.back.api.entity.models.Factura;
import co.com.tienda.virtual.back.api.entity.models.Productos;
import co.com.tienda.virtual.back.api.entity.models.ProductosFactura;
import co.com.tienda.virtual.back.api.utils.ConstruirFactura;
import net.bytebuddy.dynamic.loading.PackageDefinitionStrategy.Definition.Undefined;

@Service
public class FacturaService implements IFacturaService {

	private Logger log = LoggerFactory.getLogger(FacturaService.class);

	private final double iva = 0.16;
	private final Integer productoActivo = 1;
	private final Integer productoInactivo = 0;

	@Autowired
	private IFacturaDao facturaDao;
	
	@Autowired 
	private ConstruirFactura construirFac;

	@Autowired
	private IProductosFacturaDao proFacturaDao;

	@Autowired
	private IProductosService productosService;

	@Override
	public List<FacturaDto> findAll() {
		List<Factura> listaFacturas = facturaDao.findAll();
		List<FacturaDto> listadoFinal = new ArrayList<FacturaDto>();
		
		if (!listaFacturas.isEmpty()) {
			for (Factura factura : listaFacturas) {
				FacturaDto facturaFinal = construirFac.buildFactura(factura);
				listadoFinal.add(facturaFinal);
			}
		}
		
		return listadoFinal;
	}

	@Override
	@Transactional(readOnly = true)
	public FacturaDto findById(Integer id) {
		Factura fact = facturaDao.findById(id).orElse(null);
		FacturaDto facturaFinal = construirFac.buildFactura(fact);
		return facturaFinal;
	}

	@Override
	@Transactional()
	public FacturaDto createFactura(Factura factura) throws DataAccessException {
		Factura newFactura = null;

		double totalFactura = 0;
		for (ProductosFactura product : factura.getProductos()) {
			product.setEstado(productoActivo);
			product.setFactura(factura);
			totalFactura = (product.getCantidad() * product.getProducto().getValor()) + totalFactura;
			product.getProducto().setStock(product.getProducto().getStock() - product.getCantidad());
			productosService.updateProduct(product.getProducto());
		}
		factura.setTotalFactura((totalFactura * iva) + totalFactura);

		newFactura = facturaDao.save(factura);
		FacturaDto facturaFinal = construirFac.buildFactura(newFactura);
		return facturaFinal;
	}

	@Override
	public FacturaDto updateFactura(Factura factura) throws DataAccessException {
		Factura facturaActual = facturaDao.getById(factura.getId());
		if (facturaActual != null) {

			double totalFactura = 0;
			for (ProductosFactura product : factura.getProductos()) {
				product.setFactura(facturaActual);

				if (product.getEstado() == productoInactivo) {
					proFacturaDao.save(product);

					product.getProducto().setStock(product.getProducto().getStock() + product.getCantidad());
					productosService.updateProduct(product.getProducto());
				}

				if (product.getId() == null) {

					Productos productoActual = productosService.findById(product.getProducto().getId());
					productoActual.setStock(productoActual.getStock() - product.getCantidad());
					productosService.updateProduct(product.getProducto());
				}

				if (product.getEstado() == productoActivo) {
					totalFactura = (product.getCantidad() * product.getProducto().getValor()) + totalFactura;
				}
			}

			facturaActual.setTotalFactura((totalFactura * iva) + totalFactura);
		}

		facturaActual.setProductos(factura.getProductos());

		facturaActual.setEstadoFactura(factura.getEstadoFactura());

		Factura facturaActualizada = facturaDao.save(facturaActual);
		
		FacturaDto facturaFinal = construirFac.buildFactura(facturaActualizada);
				
		return facturaFinal;
	}

	@Override
	public void changeState(Factura factura) {
		// TODO Auto-generated method stub

	}
}
