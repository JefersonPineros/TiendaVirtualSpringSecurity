package co.com.tienda.virtual.back.api.services;

import java.util.List;

import org.springframework.dao.DataAccessException;

import co.com.tienda.virtual.back.api.entity.models.Categorias;
import co.com.tienda.virtual.back.api.entity.models.Productos;

public interface IProductosService {
	
	public List<Productos> findAll();
	
	public Productos findById(Integer id);
	
	public Productos findByCode(String code);
	
	public Productos createProduct(Productos producto) throws DataAccessException;
	
	public Productos updateProduct(Productos productos);
	
	public void deleteProduct(Integer id);
	
	public List<Categorias> findaAllCategorias();
	
	public Categorias findByIdCategorias(Integer id);
	
	public Categorias createCategorias(Categorias categoria);
	
	public void deleteCategorias(Integer id);
	
}
