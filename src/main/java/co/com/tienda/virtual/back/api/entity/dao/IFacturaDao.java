package co.com.tienda.virtual.back.api.entity.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import co.com.tienda.virtual.back.api.entity.models.Factura;

public interface IFacturaDao extends JpaRepository<Factura, Integer> {

	/*
	 * Ejemplo query nativo
	@Modifying
	@Query(
			value = "UPDATE FACTURAS f SET f.id_usuario = ?1 WHERE f.id_factura = ?2",
			nativeQuery = true)
	public void setFacturaUsuario(Integer IdUsuario, Integer idFactura);	
	*/
	

}
