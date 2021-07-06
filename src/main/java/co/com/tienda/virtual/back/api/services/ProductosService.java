package co.com.tienda.virtual.back.api.services;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.com.tienda.virtual.back.api.entity.dao.ICategoriasDao;
import co.com.tienda.virtual.back.api.entity.dao.IProductosDao;
import co.com.tienda.virtual.back.api.entity.models.Categorias;
import co.com.tienda.virtual.back.api.entity.models.Productos;

@Service
public class ProductosService implements IProductosService {
	
	private Logger log = LoggerFactory.getLogger(ProductosService.class);

	@Autowired
	private IProductosDao productosDao;
	
	@Autowired
	private ICategoriasDao categoriasDao;

	@Override
	public List<Productos> findAll() {
		return productosDao.findAll();
	}

	@Override
	public Productos findById(Integer id) {
		return productosDao.getById(id);
	}

	@Override
	public Productos findByCode(String code) {
		return productosDao.findByCodigo(code);
	}

	@Override
	public Productos createProduct(Productos producto) throws DataAccessException {
		return productosDao.save(producto);
	}

	@Override
	public Productos updateProduct(Productos productos) {
		return productosDao.save(productos);
	}

	@Override
	public void deleteProduct(Integer id) {
		Productos product = null;
		
		try {
			product = findById(id);
		} catch (DataAccessException e) {
			log.error(e.getMostSpecificCause().getMessage());
		}
		
		if (product == null) {
			log.info("No existe el producto");
		} else  {
			product.setState(0);
			productosDao.save(product);
		}
		
	}

	@Override
	public List<Categorias> findaAllCategorias() {
		return categoriasDao.findAll();
	}

	@Override
	public Categorias findByIdCategorias(Integer id) {
		return categoriasDao.getById(id);
	}

	@Override
	@Transactional
	public Categorias createCategorias(Categorias categoria) {
		return categoriasDao.save(categoria);
	}

	@Override
	public void deleteCategorias(Integer id) {
		categoriasDao.deleteById(id);
	}

}
