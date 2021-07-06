package co.com.tienda.virtual.back.api.entity.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import co.com.tienda.virtual.back.api.entity.models.EstadoFactura;
import co.com.tienda.virtual.back.api.entity.models.MetodosPago;
import co.com.tienda.virtual.back.api.entity.models.ProductosFactura;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class FacturaDto implements Serializable {
	
	private Integer Id;
	private double totalFactura;
	private Date fechaCompra;
	private Date fechaDespacho;
	private int state;
	private UsuarioDto usuario;
	private List<ProductosFactura> productos;
	private EstadoFactura estadoFactura;
	private MetodosPago metodoPago;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
}
