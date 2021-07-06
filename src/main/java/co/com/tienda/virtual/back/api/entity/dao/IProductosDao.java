package co.com.tienda.virtual.back.api.entity.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import co.com.tienda.virtual.back.api.entity.models.Productos;

public interface IProductosDao extends JpaRepository<Productos, Integer> {
	
	public Productos findByCodigo(String codigo);
}
