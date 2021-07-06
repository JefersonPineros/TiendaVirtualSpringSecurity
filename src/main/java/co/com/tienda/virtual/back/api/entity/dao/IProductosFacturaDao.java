package co.com.tienda.virtual.back.api.entity.dao;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import co.com.tienda.virtual.back.api.entity.models.ProductosFactura;

public interface IProductosFacturaDao extends JpaRepository<ProductosFactura, Integer> {
	@Modifying(clearAutomatically = true)
	@Transactional
	@Query(value = "INSERT INTO db_tienda_virtual.productos_factura (producto_id, factura_id ,cantidad_solicitada, estado_producto_factura) VALUES (:idProducto,:idFactura,:cantidad, :estado)", nativeQuery = true)
	public void setFacturaProducto(@Param("idProducto") Integer idProducto,@Param("idFactura") Integer idFactura,@Param("cantidad") Integer cantidad, @Param("estado") Integer estado);
	
	@Modifying(clearAutomatically = true)
	@Transactional
	@Query(value = "UPDATE db_tienda_virtual.productos_factura SET cantidad_solicitada = :cantidad, producto_id = :idProducto", nativeQuery = true)
	public void updateFacturaProducto(@Param("cantidad") Integer catidad, @Param("idProducto") Integer idProducto);
}
